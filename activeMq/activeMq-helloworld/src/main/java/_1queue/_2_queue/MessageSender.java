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
 * ��Ϣ������
 * ʹ�ö��з��͵�Ե���Ϣ
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
	public static void sendMessage(QueueSession session, QueueSender sender) throws JMSException {
		for (int i = 0; i < SEND_NUM; i++) {
			String message = "���͵�" + (i + 1) + "����Ϣ";
			
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
			//�������ӹ���
			QueueConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
			//ͨ����������һ������
			connection = factory.createQueueConnection();
			//��������
			connection.start();
			//����һ��session�Ự
			session = connection.createQueueSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			//����һ����Ϣ����
			Queue queue = session.createQueue(DESTINATION);
			//������Ϣ������
			QueueSender sender = session.createSender(queue);
			//���ó־û�ģʽ
			sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			//������Ϣ
			sendMessage(session, sender);
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
