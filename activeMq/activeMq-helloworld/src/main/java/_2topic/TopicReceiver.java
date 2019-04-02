package _2topic;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息接受者
 * 使用topic
 *
 */
public class TopicReceiver {
	//目标地址
	public static final String BROKER_URL = "tcp://localhost:61616";
	//接收目标
	public static final String DESTINATION = "topic.helloworld";
	
	
	public static void run() throws Exception {
		TopicConnection connection = null;
		TopicSession session = null;
		
		try {
			TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
			connection = factory.createTopicConnection();
			connection.start();
			session = connection.createTopicSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			
			Topic topic = session.createTopic(DESTINATION);
			TopicSubscriber subscriber = session.createSubscriber(topic);
			
			subscriber.setMessageListener(new MessageListener() {
				
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
			
			Thread.sleep(1000 * 100);
			
			session.commit();
			
			System.out.println("退出");
		} catch (Exception e) {
			throw e;
		} finally{
			if (connection != null) {
				connection.close();
			}
			if (session != null) {
				session.close();
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		TopicReceiver.run();
	}
	
}	
