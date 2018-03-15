package com.xiaopan.lambda._3_stream_example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _2_map {

	public static void main(String[] args) {
		/**
		 * map 生成的是个 1:1 映射，每个输入元素，都按照规则转换成为另外一个元素
		 */
		//转换大写
		String[] strArray = new String[] {"a", "b", "c"};
		List<String> wordList = Arrays.asList(strArray);
		
		List<String> output = wordList.stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList());
		
		output.forEach(System.out::println);
		
		//平方数
		List<Integer> nums = Arrays.asList(1,2,3,4);
		List<Integer> squareNums = nums.stream()
				.map(n -> n*n)
				.collect(Collectors.toList());
		squareNums.forEach(System.out::println);
		
	}
}
