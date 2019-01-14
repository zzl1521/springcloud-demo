package com.my.demo.springcloud.rabbitmq.base;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageQueueService implements IMessageQueueService ,RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback{

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void send(String queueName, String msg) {
		rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE, queueName, msg);
	}

	@Override
	public void send(String queueName, String msg, long times) {
		final long time=times*1000;
		DLXMessage dlxMessage = new DLXMessage(queueName, msg, time);
		MessagePostProcessor processor = new MessagePostProcessor() {
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				message.getMessageProperties().setExpiration(time + "");
				return message;
			}
		};
		dlxMessage.setExchange(MQConstant.DEFAULT_EXCHANGE);

		rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE, MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, dlxMessage, processor);
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean b, String s) {
		System.out.println("confirm--:correlationData:"+correlationData+",ack:"+b+",cause:"+s);
	}

	@Override
	public void returnedMessage(Message message, int i, String s, String s1, String s2) {
		System.out.println("return--message:"+new String(message.getBody())+",replyCode:"+i+",replyText:"+s+",exchange:"+s1+",routingKey:"+s2);
	}
}
