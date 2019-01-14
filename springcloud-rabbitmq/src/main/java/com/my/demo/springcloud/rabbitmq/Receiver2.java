package com.my.demo.springcloud.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

/**
 * Created by zhangzhile on 2018/4/5.
 */
@Component
//@RabbitListener(queues = "hello")
public class Receiver2 {

    @RabbitHandler
    public void process(String msg) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Receiver2:--------------------"+msg);
    }
}
