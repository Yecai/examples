package com.xiaopan.bio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Description: ����ʽI/O�ͻ���
 */
public class Client {
	private static int DEFAULT_SERVER_PORT = 12345; //Ĭ�϶˿ں�
	private static String DEFAULT_SERVER_IP = "127.0.0.1";
	public static void send(String expression) {
		send(DEFAULT_SERVER_PORT, expression);
	}
	public static void send(int port, String expression) {
		System.out.println("�������ʽΪ��" + expression);
		Socket socket = null;
		BufferedReader in = null;
		
//		PrintWriter out = null;
		BufferedWriter out = null;

		String result = null;
		try {
			socket = new Socket(DEFAULT_SERVER_IP, port);
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//			out = new PrintWriter(socket.getOutputStream(), true);
//			Thread.sleep(500);
			System.out.println("�����������ʽ��" + expression);
//			out.print("asdfsadfs");
//			out.write(expression);
//			out.flush();
			out.write(expression);
			out.flush();
			Thread.sleep(500);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			String a = in.readLine();
//			System.out.println("__���Ϊ��" + a);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//һЩ��Ҫ��������
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in = null;
			}
			if (out != null) {
//				out.close();
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				out = null;
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				socket = null;
			}
		}
	}
}
