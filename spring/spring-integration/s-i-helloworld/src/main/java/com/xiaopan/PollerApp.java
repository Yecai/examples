package com.xiaopan;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * �򵥵�Ӧ�ó���ÿ20�루20000���룩��ѯ��ǰϵͳʱ��2��
 * 
 * �����Ϣ�����Ժ���Ϊ��λ��ʱ�䣬��Ϣ��·�ɵ�һ����־ͨ��������
 *
 */
public class PollerApp {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("delay.xml");
	}
}
