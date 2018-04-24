package com.xiaopan.pressure;

public class Bootstrap {
	public static void main(String[] args) throws InterruptedException {
		String url = "http://www.baidu.com";
		long frequency = 200;
		PressureMachine machine = new PressureMachine(url, frequency);
		machine.start();
		
		while (true) {
			Thread.sleep(2000);
			machine.printStatus();
		}
	}
}
