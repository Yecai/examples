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
 * ��Ϣ������
 * JMS��ʽ����
 *
 */
public class MessageReceiver {

	/// Ŀ���ַ
	public static final String BROKER_URL = "tcp://localhost:61616";
	/// ����
	public static final String DESTINATION = "queue.helloworld";
	
	public static void run() throws Exception {
		
		Connection connection = null;
		Session session = null;
		
		try {
			//�������ӹ���
			ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
			//����һ������
			connection = factory.createConnection();
			//��������
			connection.start();
			//����һ��session�Ự
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			//����һ����Ϣ����
			Destination destination = session.createQueue(DESTINATION);
			//����һ����Ϣ������
			MessageConsumer consumer = session.createConsumer(destination);
			
			while (true) {
				//������Ϣ����ʱ100��
				Message message = consumer.receive(1000 * 100);
				
				TextMessage text = (TextMessage) message;
				if (text != null) {
					System.out.println("���գ�" + text.getText());
				} else {
					System.out.println("��ʱ�˳�");
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
