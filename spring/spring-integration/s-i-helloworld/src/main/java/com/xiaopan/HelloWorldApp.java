package com.xiaopan;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;

/**
 * 演示一个基本的消息端点（message endpoint)，他简单的将问候语“Hello”加到消息字符串前面。
 * 这是一个非常低级的示例，直接将消息通道用于输入和输出。
 * 注意，输出通道配置了一个queue子元素。因此，它是一个PollableChannel，它的使用者必须调用receive()方法获取信息，如例子所示
 * 
 * 消息端点配置在helloWorldDemo.xml中
 *
 */
public class HelloWorldApp {
	
	private static Log logger = LogFactory.getLog(HelloWorldApp.class);
	
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("helloWorldDemo.xml");
		
		MessageChannel inputChannel = context.getBean("inputChannel", MessageChannel.class);
		PollableChannel outputChannel = context.getBean("outputChannel", PollableChannel.class);
		
		inputChannel.send(new GenericMessage<String>("world"));
		
		logger.info("==> HelloWorldDemo: " + outputChannel.receive(0).getPayload());
		context.close();
	}
}
