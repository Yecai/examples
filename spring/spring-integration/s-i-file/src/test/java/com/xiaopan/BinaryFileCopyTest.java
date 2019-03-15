package com.xiaopan;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BinaryFileCopyTest {
	
	@Test
	public void textBinaryCopy() throws InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("fileCopyDemo-binary.xml");
		FileCopyDemoCommon.displayDirectories(context);
		Thread.sleep(5000);
	}
}
