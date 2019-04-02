package _1queue._1_jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息发送者
 * 使用JMS发送消息
 *
 */
public class MessageSender {
	
	///发送次数
	public static final int SEND_NUM = 5;
	
	//发送地址
	public static final String BROKER_URL =  "tcp://localhost:61616";
	
	//发送队列
	public static final String DESTINATION = "queue.helloworld";
	
	/**
	 * 发送消息
	 */
	public static void sendMessage(Session session, MessageProducer producer) throws JMSException {
		for (int i = 0; i < SEND_NUM; i++) {
			String message = "发送第" + (i + 1) + "条消息";
			
			TextMessage text = session.createTextMessage(message);
			
			System.out.println(message);
			producer.send(text);
		}
	}
	
	public static void run() throws Exception {
		
		Connection connection = null;
		Session session = null;
		
		try {
			//创建链接工厂
			ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
			//通过工厂创建一个连接
			connection = factory.createConnection();
			//启动连接
			connection.start();
			//创建一个session会话
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			//创建一个消息队列
			Destination destination = session.createQueue(DESTINATION);
			//创建消息制作者
			MessageProducer producer = session.createProducer(destination);
			//设置持久化模式
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			//发送消息
			sendMessage(session, producer);
			//提交会话
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (null != session) {
				session.close();
			}
			if (null != connection) {
				connection.close();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		MessageSender.run();
	}
	
}
