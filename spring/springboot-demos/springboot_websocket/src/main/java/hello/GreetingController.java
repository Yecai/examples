package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {
	
	@MessageMapping("/hello") //ʹ��MessageMappingע������ʶ���з��͵���/hello�����destination����Ϣ�����ᱻ·�ɵ�����������д���.
	@SendTo("/topic/greetings") //ʹ��SendToע������ʶ����������صĽ�������ᱻ���͵���ָ����destination����/topic/greetings��.
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) +"!");
	}
	
}