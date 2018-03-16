package com.xiaopan.thread.artbook._4_base._4_4_pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class _9_ConnectionDriver {
	static class ConnectionHandler implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (method.getName().equals("commit")) {
				TimeUnit.MILLISECONDS.sleep(100);
			}
			return null;
		}
		
	}
	
	//����һ��Connection�Ĵ�����commitʱ����100����
	public static final Connection createConnection() {
		//Proxy��̬����http://www.cnblogs.com/techyc/p/3455950.html
		return (Connection) Proxy.newProxyInstance(_9_ConnectionDriver.class.getClassLoader(), new Class[] {Connection.class}, new ConnectionHandler());
	}
}
