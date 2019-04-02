package demo;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

/**
 * 消息对象
 * 实现MessageCreator的createMessage方法创建消息
 */
public class MessageObject implements MessageCreator {

	@Override
	public Message createMessage(Session session) throws JMSException {
		return session.createTextMessage("测试消息" + System.currentTimeMillis());
	}

}
