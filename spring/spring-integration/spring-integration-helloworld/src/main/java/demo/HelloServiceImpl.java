package demo;

import org.springframework.stereotype.Service;

@Service("helloServiceImpl")
public class HelloServiceImpl implements HelloService {

	public void hello(String name) {
		System.out.println( "Hello, " + name );
	}

}
