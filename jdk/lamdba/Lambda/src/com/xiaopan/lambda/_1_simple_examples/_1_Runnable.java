package com.xiaopan.lambda._1_simple_examples;

/**
 * @Package: com.xiaopan.lambda._1_simple_examples
 * @ClassName: _1_Runnable
 * @author: panhuaxiong
 * @date: 2017��8��17�� ����10:15:31
 * @Description: lambda�Ż�runnable
 */
public class _1_Runnable {
	
	/**
	 * �ο���http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html#section2
	 */
	public static void main(String[] args) {
		System.out.println("Runnable Test =======");
		
		//lambda�Ż�ǰ
		
		Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("�Ż�ǰRunnable");
			}
		};
		
		new Thread(r1).start();
		
		
		//lambda�Ż���
		
		Runnable r3 = () -> System.out.println("�Ż���Runnable");

		new Thread(r3).start();
		
	}
	
}
