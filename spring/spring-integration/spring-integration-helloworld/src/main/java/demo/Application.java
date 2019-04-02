package demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring integration的helloworld示例（http://www.importnew.com/16538.html）
 * 
 * Spring Integration组件：
 * 1、消息 message
 * 2、通道 channel
 * 3、适配器 activator
 *
 *应用流程
 *1、Application类调用了GreeterService的greet()方法，把字符串传给” world!”给greet()；
 *2、GreeterService 用wired注入一个名为helloWorldChannel的MessageChannel。此通道利用一个MessageBuilder创建一个消 息，此消息包含” world!”字符串，然后将此消息发送给MessageChannel；
 *3、配置的service-activator负责将任何发送到helloWorldChannel的消息调度到HelloService的hello()；
 *4、HelloServiceImpl类的hello()方法调用后，”Hello, world!”就会打印到屏幕上。
 */
public class Application {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		GreeterService greeter = applicationContext.getBean(GreeterServiceImpl.class);
		greeter.greet(" world!");
	}
}
