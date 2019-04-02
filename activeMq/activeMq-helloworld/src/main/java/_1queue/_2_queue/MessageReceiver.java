package _1queue._2_queue;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息接收者
 * 使用队列接收点对点消息
 *
 */
public class MessageReceiver {

	/// 目标地址
	public static final String BROKER_URL = "tcp://localhost:61616";
	/// 队列
	public static final String DESTINATION = "queue.helloworld";
	
	public static void run() throws Exception {
		
		QueueConnection connection = null;
		QueueSession session = null;
		
		try {
			//创建链接工厂
			QueueConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
			//创建一个连接
			connection = factory.createQueueConnection();
			//启动连接
			connection.start();
			//创建一个session会话
			session = connection.createQueueSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			//创建一个消息队列
			Queue queue = session.createQueue(DESTINATION);
			//创建一个消息接收者
			QueueReceiver receiver = session.createReceiver(queue);
			
			receiver.setMessageListener(new MessageListener() {
				
				public void onMessage(Message msg) {
					if (msg != null) {
						MapMessage map = (MapMessage) msg;
						
						try {
							System.out.println(map.getLong("time") + "接收#" + map.getString("text"));
						} catch (JMSException e) {
							e.printStackTrace();
						}
					}
				}
			});
			
			//休眠100ms再关闭
			Thread.sleep(1000 * 10);
			//提交会话
			session.commit();
			System.out.println("退出");
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
