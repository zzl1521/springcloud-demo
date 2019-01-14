package com.my.demo.springcloud.rabbitmq.Controller;

import com.alibaba.fastjson.JSONObject;
import com.my.demo.springcloud.rabbitmq.base.IMessageQueueService;
import com.my.demo.springcloud.rabbitmq.base.MQConstant;
import com.my.demo.springcloud.rabbitmq.base.TaskMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangzhile on 2018/2/23.
 */
@RestController
@RefreshScope //动态刷新配置信息
public class UserController {

    private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IMessageQueueService messageQueueService;


    @RequestMapping("/user/add")
    public String addUser() {
        String message="message测试延时发送";
        //sender.send(message);
        TaskMessage taskMessage=new TaskMessage();
        taskMessage.setMessage(message);
        taskMessage.setPeriod(5);
        taskMessage.setPeriodMultiple(2);
        taskMessage.setTotalCount(3);
        messageQueueService.send(MQConstant.BANK_CALLBACK_QUEUE_NAME, JSONObject.toJSONString(taskMessage),10);

        return null;
    }


}

