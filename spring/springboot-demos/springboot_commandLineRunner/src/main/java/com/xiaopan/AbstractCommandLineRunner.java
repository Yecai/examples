package com.xiaopan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AbstractCommandLineRunner implements CommandLineRunner{

	private Integer idx = 1;
	
    @Override
    public void run(String... args) throws Exception {
        while (true) {
        	Thread.sleep(1000);
        	System.out.println("hello world " + idx++ + "," + System.currentTimeMillis());
		}
    }

}
