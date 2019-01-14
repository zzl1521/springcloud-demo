package com.my.demo.springcloud.rabbitmq;

import com.my.demo.springcloud.rabbitmq.base.IReceiverProcess;
import com.my.demo.springcloud.rabbitmq.base.TaskMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by zhangzhile on 2018/4/9.
 */
@Service
public class ReceiverProcessService implements IReceiverProcess {

    private static final Logger LOGGER= LoggerFactory.getLogger(ReceiverProcessService.class);

    @Override
    public boolean process(TaskMessage taskMessage) {
        LOGGER.info("Receiver:--------------------" + taskMessage.getMessage());
        return false;
    }
}
