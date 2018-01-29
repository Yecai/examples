package com.xiaopan.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("127.0.0.1", 12345);
		
		OutputStream out = socket.getOutputStream();
		out.write("hahaha".getBytes());
		
		socket.close();
	}
}
