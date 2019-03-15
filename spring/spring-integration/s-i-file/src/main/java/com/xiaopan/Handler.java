package com.xiaopan;

import java.io.File;

/**
 * 文件处理
 *
 */
public class Handler {
	
	public String handleString(String input) {
		System.out.println("Copying text: " + input);
		return input.toUpperCase();
	}
	
	public File handlerFile(File input) {
		System.out.println("Copying file: " + input.getAbsolutePath());
		return input;
	}
	
	public byte[] handlerBytes(byte[] input) {
		System.out.println("Copying " + input.length + "bytes ...");
		return new String(input).toUpperCase().getBytes();
	}
}
