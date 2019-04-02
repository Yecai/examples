package demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring integration��helloworldʾ����http://www.importnew.com/16538.html��
 * 
 * Spring Integration�����
 * 1����Ϣ message
 * 2��ͨ�� channel
 * 3�������� activator
 *
 *Ӧ������
 *1��Application�������GreeterService��greet()���������ַ��������� world!����greet()��
 *2��GreeterService ��wiredע��һ����ΪhelloWorldChannel��MessageChannel����ͨ������һ��MessageBuilder����һ���� Ϣ������Ϣ������ world!���ַ�����Ȼ�󽫴���Ϣ���͸�MessageChannel��
 *3�����õ�service-activator�����κη��͵�helloWorldChannel����Ϣ���ȵ�HelloService��hello()��
 *4��HelloServiceImpl���hello()�������ú󣬡�Hello, world!���ͻ��ӡ����Ļ�ϡ�
 */
public class Application {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		GreeterService greeter = applicationContext.getBean(GreeterServiceImpl.class);
		greeter.greet(" world!");
	}
}
