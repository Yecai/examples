package com.xiaopan;

import java.io.File;

import org.springframework.beans.DirectFieldAccessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;

/**
 * 显示输入输出的文件目录名称
 *
 */
public class FileCopyDemoCommon {

	public static void displayDirectories(ApplicationContext context) {
		File inDir = (File) new DirectFieldAccessor(context.getBean(FileReadingMessageSource.class)).getPropertyValue("directory");
		
		LiteralExpression expression = (LiteralExpression) new DirectFieldAccessor(context.getBean(FileWritingMessageHandler.class)).getPropertyValue("destinationDirectoryExpression");
		
		File outDir = new File(expression.getValue());
		
		System.out.println("Input directory is: " + inDir.getAbsolutePath());
		System.out.println("Output directory is: " + outDir.getAbsolutePath());
		System.out.println("==================================================");
	}
	
	public static void main(String[] args) throws InterruptedException {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("fileCopyDemo-binary.xml");
//		AbstractApplicationContext context = new ClassPathXmlApplicationContext("fileCopyDemo-file.xml");
//		AbstractApplicationContext context = new ClassPathXmlApplicationContext("fileCopyDemo-text.xml");
		FileCopyDemoCommon.displayDirectories(context);
	}
}
