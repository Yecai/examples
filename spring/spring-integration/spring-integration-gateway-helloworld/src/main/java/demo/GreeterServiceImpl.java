package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("greeterServiceImpl")
public class GreeterServiceImpl implements GreeterService {

	@Autowired
	private HelloService helloWorldGateway;
	
	public void greetWithReplay(String name) {
		String replay = helloWorldGateway.getHelloMessage(name);
		
		System.out.println(replay);
	}
	
	
}
