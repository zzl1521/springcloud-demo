package com.my.demo.springcloud.rabbitmq;

import com.my.demo.springcloud.rabbitmq.base.MQConstant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhangzhile on 2018/4/5.
 */
@Component
public class Sender implements RabbitTemplate.ConfirmCallback{

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String message) {
        //String msg = "hello rabbitmq:"+new Date();
        //System.out.println("Sender:--------------------"+message);
        this.rabbitTemplate.convertAndSend(MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME, "sender-"+message);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println(" 回调id:" + correlationData);
        if (b) {
            System.out.println("消息成功消费");
        } else {
            System.out.println("消息消费失败:" + s);
        }
    }
}