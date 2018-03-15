package com.xiaopan.lambda._3_stream_example;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _5_foreach {
	public static void main(String[] args) {
		// ��ӡ����
//		roster.stream()
//		 .filter(p -> p.getGender() == Person.Sex.MALE)
//		 .forEach(p -> System.out.println(p.getName()));
		
		//forEach �� terminal �����������ִ�к�Stream ��Ԫ�ؾͱ������ѡ����ˣ����޷���һ�� Stream �������� terminal ����
		//�������ƹ��ܵ� intermediate ���� peek ���Դﵽ����Ŀ��
		Stream.of("one", "two", "three", "four")
		 .filter(e -> e.length() > 3)
		 .peek(e -> System.out.println("Filtered value: " + e))
		 .map(String::toUpperCase)
		 .peek(e -> System.out.println("Mapped value: " + e))
		 .collect(Collectors.toList());
	}
}
