package com.xiaopan.thread.artbook._4_base._4_3_thread_communication;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @Package: com.xiaopan.thread.artbook._4_base
 * @ClassName: _6_piped
 * @date: 2017��8��22�� ����5:27:02
 * @Description: �ܵ�����/�����
 * �ܵ�����/���������ͨ���ļ�����/�����������������/�������֮ͬ�����ڣ�����Ҫ�����߳�֮������ݴ��䣬�������ý��Ϊ�ڴ�
 * �ܵ�����/�������Ҫ����������4�־���ʵ�֣�PipedOutputStream��PipedInputStream��PipedReader��PipedWriter��ǰ���������ֽڣ��������������ַ�
 */
public class _6_piped {
	
	//�κ�main�̵߳������ͨ��PipedWriterд�룬��printThread����һ��ͨ��PipedReader�����ݶ�������ӡ
	public static void main(String[] args) throws IOException {
		PipedWriter out = new PipedWriter();
		PipedReader in = new PipedReader();
		//����������������������ӣ�������ʹ��ʱ���׳�IOException
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
