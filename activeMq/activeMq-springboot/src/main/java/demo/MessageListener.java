package demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消息监听者
 *
 */
@Component
public class MessageListener {
	
	@JmsListener(destination="queue.helloworld.springboot")
	public void removeMessage(String msg) {
		System.out.println("监听接收到的消息是：" + msg);
	}
	
}
