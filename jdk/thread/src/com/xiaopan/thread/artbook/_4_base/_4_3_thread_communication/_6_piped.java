package com.xiaopan.thread.artbook._4_base._4_3_thread_communication;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @Package: com.xiaopan.thread.artbook._4_base
 * @ClassName: _6_piped
 * @date: 2017年8月22日 下午5:27:02
 * @Description: 管道输入/输出流
 * 管道输入/输出流和普通的文件输入/输出流或者网络输入/输出流不同之处在于，它主要用于线程之间的数据传输，而传输的媒介为内存
 * 管道输入/输出流主要包括了如下4种具体实现：PipedOutputStream、PipedInputStream、PipedReader和PipedWriter，前两种面向字节，而后两种面向字符
 */
public class _6_piped {
	
	//任何main线程的输入均通过PipedWriter写入，而printThread在另一端通过PipedReader将内容读出并打印
	public static void main(String[] args) throws IOException {
		PipedWriter out = new PipedWriter();
		PipedReader in = new PipedReader();
		//将输出流和输入流进行连接，否则在使用时会抛出IOException
		out.connect(in);
		
		Thread printThread = new Thread(new Print(in), "PrintThread");
		printThread.start();
		
		int receive = 0;
		try {
			while ((receive = System.in.read()) != -1) {
				out.write(receive);
			}
		} finally {
			out.close();
		}
		
	}
	
	static class Print implements Runnable {
		private PipedReader in;
		public Print(PipedReader in) {
			this.in = in;
		}
		@Override
		public void run() {
			int receive = 0;
			try {
				while ((receive = in.read()) != -1) {
					System.out.print((char) receive);
				}
			} catch (IOException e) {
			}
		}
	}
}
