package com.xiaopan.lambda._3_stream_example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class _3_flatMap {

	public static void main(String[] args) {
		/**
		 * ��һЩ��������һ�Զ�ӳ���ϵ�ģ���ʱ��Ҫ flatMap
		 */
		Stream<List<Integer>> inputStream = Stream.of(
				Arrays.asList(1),
				Arrays.asList(2,3),
				Arrays.asList(4,5,6)
				);
		//flatMap �� input Stream �еĲ㼶�ṹ��ƽ�������ǽ���ײ�Ԫ�س�����ŵ�һ������ output ���� Stream �����Ѿ�û�� List �ˣ�����ֱ�ӵ�����
		Stream<Integer> outputStream = inputStream
				.flatMap((childList) -> childList.stream());
		
		outputStream.forEach(System.out::println);
		
		
	}
}
