package com.xiaopan.lambda._3_stream_example;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _5_foreach {
	public static void main(String[] args) {
		// 打印名称
//		roster.stream()
//		 .filter(p -> p.getGender() == Person.Sex.MALE)
//		 .forEach(p -> System.out.println(p.getName()));
		
		//forEach 是 terminal 操作，因此它执行后，Stream 的元素就被“消费”掉了，你无法对一个 Stream 进行两次 terminal 运算
		//具有相似功能的 intermediate 操作 peek 可以达到上述目的
		Stream.of("one", "two", "three", "four")
		 .filter(e -> e.length() > 3)
		 .peek(e -> System.out.println("Filtered value: " + e))
		 .map(String::toUpperCase)
		 .peek(e -> System.out.println("Mapped value: " + e))
		 .collect(Collectors.toList());
	}
}
