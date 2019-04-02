package demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring integration的带返回值的helloworld示例（http://www.importnew.com/16538.html）
 * 
 * 1、Application类调用GreeterService的greetWithReplay()方法；
 * 2、greetWithReplay()调用getHelloMessage()方法，此方法被认为是一个HelloService方法(确切的说它是一个网关)；
 * 3、网关实现了HelloService接口中的方法，该方法通过helloWorldWithReplyChannel通道对请求进行调度；
 * 4、通过配置服务激活器(service-activator)，任何发给helloWorldWithReplyChannel的消息都会传给helloServiceImpl的getHelloMessage()方法；
 * 5、helloServiceImpl构造了本次响应并将其返回；
 * 6、服务激活器(service-activator)通过下面任意一种方式处理响应：一种是定义在服务激活器中的输出通道(output-channel),另一种是定义在消息头重的回复通道。网关自动创建一个临时的匿名、点对点回复通道；监听此通道，并将其添加到消息的replyHeader中；
 * 7、通道接受这个响应，将其作为消息，通过回复通道，将其转换成服务中定义的返回值；
 * 8、最后网关返回响应给调用者，如本例的GreeterServiceImpl。
 */
public class Application {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		GreeterService greeter = applicationContext.getBean(GreeterServiceImpl.class);
		greeter.greetWithReplay(" world!");
	}
}
