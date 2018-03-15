package com.xiaopan.lambda._3_stream_example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _2_map {

	public static void main(String[] args) {
		/**
		 * map ���ɵ��Ǹ� 1:1 ӳ�䣬ÿ������Ԫ�أ������չ���ת����Ϊ����һ��Ԫ��
		 */
		//ת����д
		String[] strArray = new String[] {"a", "b", "c"};
		List<String> wordList = Arrays.asList(strArray);
		
		List<String> output = wordList.stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList());
		
		output.forEach(System.out::println);
		
		//ƽ����
		List<Integer> nums = Arrays.asList(1,2,3,4);
		List<Integer> squareNums = nums.stream()
				.map(n -> n*n)
				.collect(Collectors.toList());
		squareNums.forEach(System.out::println);
		
	}
}
