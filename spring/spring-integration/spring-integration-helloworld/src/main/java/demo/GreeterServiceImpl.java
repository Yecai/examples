package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service("greeterServiceImpl")
public class GreeterServiceImpl implements GreeterService {

	@Autowired
    private MessageChannel helloWorldChannel;
    
	public void greet(String name) {
		//ÓÀ¾Ã×èÈû
		helloWorldChannel.send( MessageBuilder.withPayload( name ).build() );
		
		//³¬Ê±×èÈû
//		helloWorldChannel.send(message, timeout);
	}

}
