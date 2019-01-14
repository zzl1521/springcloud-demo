package com.my.demo.springcloud.rabbitmq.base;

public class MQConstant {

	// exchange name
	public static final String DEFAULT_EXCHANGE = "bank";

	// DLX QUEUE
	public static final String DEFAULT_DEAD_LETTER_QUEUE_NAME = "bank.dead.letter.queue";

	// DLX repeat QUEUE 死信转发队列
	public static final String DEFAULT_REPEAT_TRADE_QUEUE_NAME = "bank.repeat.trade.queue";

	// 回调消息队列名称
	public static final String BANK_CALLBACK_QUEUE_NAME = "bank_callback";

}
