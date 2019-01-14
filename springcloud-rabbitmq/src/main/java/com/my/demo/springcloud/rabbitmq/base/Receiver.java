package com.my.demo.springcloud.rabbitmq.base;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by zhangzhile on 2018/4/5.
 */
@Component
//@RabbitListener(queues = MQConstant.BANK_CALLBACK_QUEUE_NAME)
public class Receiver implements ChannelAwareMessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private IMessageQueueService messageQueueService;
    @Autowired
    private IReceiverProcess receiverProcess;

    //@RabbitHandler
    public boolean process(TaskMessage taskMessage) {
        LOGGER.info("Receiver:--------------------" + taskMessage.getMessage());
        return false;
    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String body = new String(message.getBody());
        TaskMessage taskMessage = JSONObject.parseObject(body, TaskMessage.class);
        //业务处理，放到action层，并返回处理成功还是异常的flag
        boolean mqFlag = receiverProcess.process(taskMessage);

        //还有一个点就是如何获取mq消息的报文部分message？
        //String message=new String(arg0.getBody(),"UTF-8");
        basicACK(message, channel);
        if (mqFlag) {
            //basicACK(message,channel);//处理正常--ack
            LOGGER.info("处理正常---------{}", body);
        } else {
            int executeCount = taskMessage.getExecuteCount();
            if (taskMessage.getExecuteCount() >= taskMessage.getTotalCount() - 1) {
                //TODO 报警等处理
                LOGGER.error("报警等处理====================");
                return;
            }
            taskMessage.setExecuteCount(executeCount + 1);
            long time = taskMessage.getPeriod() * taskMessage.getPeriodMultiple(); //秒
            taskMessage.setPeriod(time);
            messageQueueService.send(MQConstant.BANK_CALLBACK_QUEUE_NAME, JSONObject.toJSONString(taskMessage), time);
            //basicNACK(message,channel);//处理异常--nack
            LOGGER.info("处理异常-已重发---------{}", JSONObject.toJSONString(taskMessage));
        }
    }

    //正常消费掉后通知mq服务器移除此条mq
    private void basicACK(Message message, Channel channel) {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            LOGGER.error("通知服务器移除mq时异常，异常信息：" + e);
        }
    }

    //处理异常，mq重回队列
    private void basicNACK(Message message, Channel channel) {
        try {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        } catch (IOException e) {
            LOGGER.error("mq重新进入服务器时出现异常，异常信息：" + e);
        }
    }
}
