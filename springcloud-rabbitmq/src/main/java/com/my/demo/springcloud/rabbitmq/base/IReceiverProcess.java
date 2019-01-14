package com.my.demo.springcloud.rabbitmq.base;

import com.my.demo.springcloud.rabbitmq.base.TaskMessage;

/**
 * Created by zhangzhile on 2018/4/8.
 */
public interface IReceiverProcess {

     boolean process(TaskMessage taskMessage);

}
