package com.xiaopan.thread.artbook._4_base._4_4_pool;

import java.sql.Connection;
import java.util.LinkedList;

public class _9_ConnectionPool {
	
	private LinkedList<Connection> pool = new LinkedList<>();
	
	public _9_ConnectionPool(int initialSize) {
		if (initialSize > 0) {
			for (int i = 0; i < initialSize; i++) {
				pool.addLast(_9_ConnectionDriver.createConnection());
			}
		}
	}
	
	public void releaseConnection(Connection connection) {
		if (connection != null) {
			synchronized (pool) {
				//�����ͷź���Ҫ����֪ͨ�����������������ܹ���֪�����ӳ����Ѿ��黹��һ������
				pool.addLast(connection);
				pool.notifyAll();
			}
		}
	}
	
	//��mills���޷���ȡ������ �����᷵��null
	public Connection fetchConnection(long mills) throws InterruptedException {
		synchronized (pool) {
			//��ȫ��ʱ
			if (mills <= 0) {
				while (pool.isEmpty()) {
					pool.wait();
				}
				return pool.removeFirst();
			} else {
				long future = System.currentTimeMillis() + mills;
				long remaining = mills;
				while (pool.isEmpty() && remaining > 0) {
					pool.wait(remaining);
					remaining = future - System.currentTimeMillis();
				}
				Connection result = null;
				if (!pool.isEmpty()) {
					result = pool.removeFirst();
				}
				return result;
			}
		}
	}
}
