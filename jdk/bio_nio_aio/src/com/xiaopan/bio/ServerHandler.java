package com.xiaopan.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.script.ScriptException;

/**
 * @Description: �ͻ�����Ϣ����
 * ���ڴ���һ���ͻ��˵�Socket��· 
 */
public class ServerHandler implements Runnable {
	
	private Socket socket;
	
	public ServerHandler(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			String expression;
			String result;
			while (true) {
				//ͨ��BufferedReader��ȡһ��
				//����Ѿ����������β��������null���˳�ѭ��
				//����õ��ǿ�ֵ���ͳ��Լ�����������
				System.out.println("�������ȴ���Ϣ");
				expression = in.readLine();
				if (expression == null) break;
				System.out.println("�������յ���Ϣ��" + expression);
				try {
					result = Calculator.cal(expression).toString();
				} catch (ScriptException e) {
					result = "�������" + e.getMessage();
				}
				out.print(result);
			}
		} catch (IOException e) {
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
				out.close();
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
