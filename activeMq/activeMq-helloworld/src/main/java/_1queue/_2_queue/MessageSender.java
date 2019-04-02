package _1queue._2_queue;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息发送者
 * 使用队列发送点对点消息
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
	public static void sendMessage(QueueSession session, QueueSender sender) throws JMSException {
		for (int i = 0; i < SEND_NUM; i++) {
			String message = "发送第" + (i + 1) + "条消息";
			
			MapMessage map = session.createMapMessage();
			map.setString("text", message);
			map.setLong("time", System.currentTimeMillis());
			
			System.out.println(map);
			sender.send(map);
		}
	}
	
	public static void run() throws Exception {
		
		QueueConnection connection = null;
		QueueSession session = null;
		
		try {
			//创建链接工厂
			QueueConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
			//通过工厂创建一个连接
			connection = factory.createQueueConnection();
			//启动连接
			connection.start();
			//创建一个session会话
			session = connection.createQueueSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			//创建一个消息队列
			Queue queue = session.createQueue(DESTINATION);
			//创建消息制作者
			QueueSender sender = session.createSender(queue);
			//设置持久化模式
			sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			//发送消息
			sendMessage(session, sender);
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
