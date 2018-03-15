package com.xiaopan.lambda._3_stream_example;

import java.util.stream.Stream;

public class _6_reduce {
	public static void main(String[] args) {
		
		//�ַ������ӣ�concat = "ABCD"
		//reduce��һ���������հ��ַ�����Ϊ��ʼֵ���ڶ���������String::concat��Ϊ BinaryOperator
		String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
		System.out.println(concat);
		
		// ����Сֵ��minValue = -3.0
		double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
		System.out.println(minValue);
		
		//��ͣ�sumValue = 10, ����ʼֵ
		int sumValue = Stream.of(1,2,3,4).reduce(0, Integer::sum);
		System.out.println(sumValue);
		//��ͣ�sumValue=10������ʼֵ
		//û����ʼֵ�� reduce()�����ڿ���û���㹻��Ԫ�أ����ص��� Optional
		sumValue = Stream.of(1,2,3,4).reduce(Integer::sum).get();
		System.out.println(sumValue);
		
		//���ˣ��ַ������ӣ�concat = "ace"
		concat = Stream.of("a","B","c","D","e","F")
				.filter(x -> x.compareTo("Z") > 0)
				.reduce("", String::concat);
		System.out.println(concat);
		
		
	}
}
