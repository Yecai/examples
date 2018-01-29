package com.xiaopan.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.script.ScriptException;

/**
 * @Description: 客户端消息处理
 * 用于处理一个客户端的Socket链路 
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
				//通过BufferedReader读取一行
				//如果已经读到输出流尾部，返回null，退出循环
				//如果得到非空值，就尝试计算结果并返回
				System.out.println("服务器等待消息");
				expression = in.readLine();
				if (expression == null) break;
				System.out.println("服务器收到消息：" + expression);
				try {
					result = Calculator.cal(expression).toString();
				} catch (ScriptException e) {
					result = "计算错误：" + e.getMessage();
				}
				out.print(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//一些必要的清理工作
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
