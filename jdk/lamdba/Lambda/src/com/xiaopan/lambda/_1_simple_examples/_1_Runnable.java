package com.xiaopan.lambda._1_simple_examples;

/**
 * @Package: com.xiaopan.lambda._1_simple_examples
 * @ClassName: _1_Runnable
 * @author: panhuaxiong
 * @date: 2017年8月17日 上午10:15:31
 * @Description: lambda优化runnable
 */
public class _1_Runnable {
	
	/**
	 * 参考：http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html#section2
	 */
	public static void main(String[] args) {
		System.out.println("Runnable Test =======");
		
		//lambda优化前
		
		Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("优化前Runnable");
			}
		};
		
		new Thread(r1).start();
		
		
		//lambda优化后
		
		Runnable r3 = () -> System.out.println("优化后Runnable");

		new Thread(r3).start();
		
	}
	
}
