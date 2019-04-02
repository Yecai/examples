package demo;

import org.springframework.stereotype.Service;

@Service("helloServiceImpl")
public class HelloServiceImpl implements HelloService {

	public String getHelloMessage(String name) {
		return "Hello, " + name;
	}

}
