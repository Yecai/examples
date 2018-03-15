package com.xiaopan.lambda._3_stream_example;

import java.util.stream.Stream;

public class _6_reduce {
	public static void main(String[] args) {
		
		//字符串连接，concat = "ABCD"
		//reduce第一个参数（空白字符）即为起始值，第二个参数（String::concat）为 BinaryOperator
		String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
		System.out.println(concat);
		
		// 求最小值，minValue = -3.0
		double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
		System.out.println(minValue);
		
		//求和，sumValue = 10, 有起始值
		int sumValue = Stream.of(1,2,3,4).reduce(0, Integer::sum);
		System.out.println(sumValue);
		//求和，sumValue=10，无起始值
		//没有起始值的 reduce()，由于可能没有足够的元素，返回的是 Optional
		sumValue = Stream.of(1,2,3,4).reduce(Integer::sum).get();
		System.out.println(sumValue);
		
		//过滤，字符串连接，concat = "ace"
		concat = Stream.of("a","B","c","D","e","F")
				.filter(x -> x.compareTo("Z") > 0)
				.reduce("", String::concat);
		System.out.println(concat);
		
		
	}
}
