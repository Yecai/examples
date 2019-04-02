package demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring integration�Ĵ�����ֵ��helloworldʾ����http://www.importnew.com/16538.html��
 * 
 * 1��Application�����GreeterService��greetWithReplay()������
 * 2��greetWithReplay()����getHelloMessage()�������˷�������Ϊ��һ��HelloService����(ȷ�е�˵����һ������)��
 * 3������ʵ����HelloService�ӿ��еķ������÷���ͨ��helloWorldWithReplyChannelͨ����������е��ȣ�
 * 4��ͨ�����÷��񼤻���(service-activator)���κη���helloWorldWithReplyChannel����Ϣ���ᴫ��helloServiceImpl��getHelloMessage()������
 * 5��helloServiceImpl�����˱�����Ӧ�����䷵�أ�
 * 6�����񼤻���(service-activator)ͨ����������һ�ַ�ʽ������Ӧ��һ���Ƕ����ڷ��񼤻����е����ͨ��(output-channel),��һ���Ƕ�������Ϣͷ�صĻظ�ͨ���������Զ�����һ����ʱ����������Ե�ظ�ͨ����������ͨ������������ӵ���Ϣ��replyHeader�У�
 * 7��ͨ�����������Ӧ��������Ϊ��Ϣ��ͨ���ظ�ͨ��������ת���ɷ����ж���ķ���ֵ��
 * 8��������ط�����Ӧ�������ߣ��籾����GreeterServiceImpl��
 */
public class Application {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		GreeterService greeter = applicationContext.getBean(GreeterServiceImpl.class);
		greeter.greetWithReplay(" world!");
	}
}
