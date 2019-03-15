package com.xiaopan;

import java.io.File;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 文件处理器
 *
 */
public class FileProcessor {
	
	private final Random random = new Random();
	private final Log logger = LogFactory.getLog(FileProcessor.class);
	
	public File process(File file) throws InterruptedException {
		Thread.sleep(random.nextInt(10) * 500);
		logger.info("Processing File:" + file);
		return file;
	}
}
