package com.xiaopan.lambda._3_stream_example;

import java.util.stream.Stream;

public class _12_interate {
	public static void main(String[] args) {
		//iterate �� reduce �������񣬽���һ������ֵ����һ�� UnaryOperator������ f����Ȼ������ֵ��Ϊ Stream �ĵ�һ��Ԫ�أ�f(seed) Ϊ�ڶ�����f(f(seed)) ���������Դ����ơ�
		//�� Stream.generate ��£��� iterate ʱ��ܵ������� limit �����Ĳ��������� Stream ��С
		//����һ���Ȳ�����
		Stream.iterate(0, n -> n + 3).limit(10).forEach(x -> System.out.print(x + " "));
		
	}
}
