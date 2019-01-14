package com.my.demo.springcloud;

import com.my.demo.springcloud.rabbitmq.Sender;
import com.my.demo.springcloud.rabbitmq.Sender2;
import com.my.demo.springcloud.rabbitmq.base.IMessageQueueService;
import com.my.demo.springcloud.rabbitmq.base.MQConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhangzhile on 2018/4/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = RabbitmqHelloApplication.class)
@ContextConfiguration(classes = RabbitmqServiceApplication.class)
public class RabbitmqHelloApplicationTests {

    @Autowired
    private Sender sender;
    @Autowired
    private Sender2 sender2;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private IMessageQueueService messageQueueService;


    @Test
    public void contextLoads() {

        String message="message测试延时发送";
        //sender.send(message);

        messageQueueService.send(MQConstant.BANK_CALLBACK_QUEUE_NAME,message,10000);

        /*for (int i=0;i<3;i++){
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    String message="message";
                    sender.send(message);
                }
            });
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    String message="message";
                    sender2.send(message);
                }
            });
        }*/
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
