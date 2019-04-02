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
 * ��Ϣ������
 * ʹ��JMS������Ϣ
 *
 */
public class MessageSender {
	
	///���ʹ���
	public static final int SEND_NUM = 5;
	
	//���͵�ַ
	public static final String BROKER_URL =  "tcp://localhost:61616";
	
	//���Ͷ���
	public static final String DESTINATION = "queue.helloworld";
	
	/**
	 * ������Ϣ
	 */
	public static void sendMessage(Session session, MessageProducer producer) throws JMSException {
		for (int i = 0; i < SEND_NUM; i++) {
			String message = "���͵�" + (i + 1) + "����Ϣ";
			
			TextMessage text = session.createTextMessage(message);
			
			System.out.println(message);
			producer.send(text);
		}
	}
	
	public static void run() throws Exception {
		
		Connection connection = null;
		Session session = null;
		
		try {
			//�������ӹ���
			ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
			//ͨ����������һ������
			connection = factory.createConnection();
			//��������
			connection.start();
			//����һ��session�Ự
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			//����һ����Ϣ����
			Destination destination = session.createQueue(DESTINATION);
			//������Ϣ������
			MessageProducer producer = session.createProducer(destination);
			//���ó־û�ģʽ
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			//������Ϣ
			sendMessage(session, producer);
			//�ύ�Ự
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
