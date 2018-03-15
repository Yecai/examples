package com.xiaopan.lambda._3_stream_example;

import java.util.stream.Stream;

public class _4_filter {
	public static void main(String[] args) {
		
		//filter ��ԭʼ Stream ����ĳ����ԣ�ͨ�����Ե�Ԫ�ر�����������һ���� Stream
		//����ż��
		Integer[] sixNums = {1,2,3,4,5,6};
		Integer[] evens = Stream.of(sixNums)
				.filter(n -> n%2 == 0)
				.toArray(Integer[]::new);
		
		for (int i = 0; i < evens.length; i++) {
			System.out.println(evens[i]);
		}
		
		//�ѵ��ʵ�����
//		List<String> output = reader.lines()
//				.flatMap(line -> Stream.of(line.split(REGEXP)))
//				.filter(word -> word.length() > 0)
//				.collect(Collectors.toList());
	}
}
