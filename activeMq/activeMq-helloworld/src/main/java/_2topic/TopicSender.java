package _2topic;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * ��Ϣ������
 * ʹ��topic
 *
 */
public class TopicSender {
	
	//���ʹ���
	public static final int SEND_NUM = 5;
	//Ŀ���ַ
	public static final String BROKER_URL = "tcp://localhost:61616";
	//����Ŀ��
	public static final String DESTINATION = "topic.helloworld";
	
	public static void sendMessage(TopicSession session, TopicPublisher publisher) throws JMSException {
		for (int i = 0; i < SEND_NUM; i++) {
			String message = "���͵�" + ( i + 1) + "����Ϣ";
			
			MapMessage map = session.createMapMessage();
			map.setString("text", message);
			map.setLong("time", System.currentTimeMillis());
			
			System.out.println(map);
			publisher.send(map);
		}
	}
	
	public static void run() throws Exception {
		
		TopicConnection connection = null;
		TopicSession session = null;
		
		try {
			TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
			connection = factory.createTopicConnection();
			connection.start();
			session = connection.createTopicSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(DESTINATION);
			TopicPublisher publisher = session.createPublisher(topic);
			
			sendMessage(session, publisher);
			
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
		TopicSender.run();
	}
}
