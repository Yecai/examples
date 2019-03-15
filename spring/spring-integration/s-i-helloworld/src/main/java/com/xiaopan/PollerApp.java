package com.xiaopan;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 简单的应用程序，每20秒（20000毫秒）轮询当前系统时间2次
 * 
 * 结果消息包含以毫秒为单位的时间，消息被路由到一个日志通道适配器
 *
 */
public class PollerApp {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("delay.xml");
	}
}
