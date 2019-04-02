package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 */

@Component
@EnableScheduling
public class MessageProduction {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Scheduled(fixedDelay = 3000)
	public void send() {
		String msg = "测试消息队列" + System.currentTimeMillis() / 1000;
		System.out.println(msg);
		this.jmsTemplate.send("queue.helloworld.springboot", new MessageObject());
	}
}
