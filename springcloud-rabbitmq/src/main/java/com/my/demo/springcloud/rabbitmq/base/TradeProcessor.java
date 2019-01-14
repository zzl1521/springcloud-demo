package com.my.demo.springcloud.rabbitmq.base;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME, containerFactory="rabbitListenerContainerFactory")
public class TradeProcessor {  
      
    @Autowired
    private IMessageQueueService messageQueueService;
  
    @RabbitHandler
    @RabbitListener(queues = MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME)
    public void process(DLXMessage message) {
        //DLXMessage message = JSON.parseObject(content, DLXMessage.class);
        messageQueueService.send(message.getQueueName(), message.getContent());
    }  
}  
