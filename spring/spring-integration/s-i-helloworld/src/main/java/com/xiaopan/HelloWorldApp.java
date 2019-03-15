package com.xiaopan;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;

/**
 * ��ʾһ����������Ϣ�˵㣨message endpoint)�����򵥵Ľ��ʺ��Hello���ӵ���Ϣ�ַ���ǰ�档
 * ����һ���ǳ��ͼ���ʾ����ֱ�ӽ���Ϣͨ����������������
 * ע�⣬���ͨ��������һ��queue��Ԫ�ء���ˣ�����һ��PollableChannel������ʹ���߱������receive()������ȡ��Ϣ����������ʾ
 * 
 * ��Ϣ�˵�������helloWorldDemo.xml��
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
