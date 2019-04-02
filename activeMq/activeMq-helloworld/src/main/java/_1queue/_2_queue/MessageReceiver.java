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
 * ��Ϣ������
 * ʹ�ö��н��յ�Ե���Ϣ
 *
 */
public class MessageReceiver {

	/// Ŀ���ַ
	public static final String BROKER_URL = "tcp://localhost:61616";
	/// ����
	public static final String DESTINATION = "queue.helloworld";
	
	public static void run() throws Exception {
		
		QueueConnection connection = null;
		QueueSession session = null;
		
		try {
			//�������ӹ���
			QueueConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
			//����һ������
			connection = factory.createQueueConnection();
			//��������
			connection.start();
			//����һ��session�Ự
			session = connection.createQueueSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			//����һ����Ϣ����
			Queue queue = session.createQueue(DESTINATION);
			//����һ����Ϣ������
			QueueReceiver receiver = session.createReceiver(queue);
			
			receiver.setMessageListener(new MessageListener() {
				
				public void onMessage(Message msg) {
					if (msg != null) {
						MapMessage map = (MapMessage) msg;
						
						try {
							System.out.println(map.getLong("time") + "����#" + map.getString("text"));
						} catch (JMSException e) {
							e.printStackTrace();
						}
					}
				}
			});
			
			//����100ms�ٹر�
			Thread.sleep(1000 * 10);
			//�ύ�Ự
			session.commit();
			System.out.println("�˳�");
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
