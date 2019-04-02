package _1queue._1_jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息接收者
 * JMS方式接收
 *
 */
public class MessageReceiver {

	/// 目标地址
	public static final String BROKER_URL = "tcp://localhost:61616";
	/// 队列
	public static final String DESTINATION = "queue.helloworld";
	
	public static void run() throws Exception {
		
		Connection connection = null;
		Session session = null;
		
		try {
			//创建链接工厂
			ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
			//创建一个连接
			connection = factory.createConnection();
			//启动连接
			connection.start();
			//创建一个session会话
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			//创建一个消息队列
			Destination destination = session.createQueue(DESTINATION);
			//创建一个消息接收者
			MessageConsumer consumer = session.createConsumer(destination);
			
			while (true) {
				//接收消息，超时100秒
				Message message = consumer.receive(1000 * 100);
				
				TextMessage text = (TextMessage) message;
				if (text != null) {
					System.out.println("接收：" + text.getText());
				} else {
					System.out.println("超时退出");
					break;
				}
			}
			
			session.commit();
		} catch (Exception e) {
			
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		MessageReceiver.run();
	}
}
