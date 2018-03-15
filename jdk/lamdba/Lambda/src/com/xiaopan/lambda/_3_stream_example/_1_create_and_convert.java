package com.xiaopan.lambda._3_stream_example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Package: com.xiaopan.lambda._3_stream_example
 * @ClassName: _1_create_and_convert
 * @author: panhuaxiong
 * @date: 2017年8月17日 下午2:40:10
 * @Description: 流的构造与转换
 * https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/
 */
public class _1_create_and_convert {
	@SuppressWarnings({ "unused", "rawtypes" })
	public static void main(String[] args) {
		
		//从独立数据创建
		Stream<String> stream = Stream.of("a", "b", "c");
		
		//通过Arrays创建
		String[] strArray = new String[] {"a", "b", "c"};
		stream = Stream.of(strArray);
		stream = Arrays.stream(strArray);
		
		//Collection
		List<String> list = Arrays.asList(strArray);
		stream = list.stream();
		
		//基础类型流IntStream、LongStream、DoubleStream
		IntStream.of(new int[] {1,2,3}).forEach(System.out::println);
		IntStream.range(1, 3).forEach(System.out::println);
		IntStream.rangeClosed(1, 3).forEach(System.out::println);
		
		//流转换为其他数据结构
		//转换为数组
		String[] strArray1 = stream.toArray(String[]::new);
		//转换为Collection
		List<String> list1 = stream.collect(Collectors.toList());
		List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
		Set set1 = stream.collect(Collectors.toSet());
		Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
		//转换为string
		String str = stream.collect(Collectors.joining()).toString();
		
		
	}
}
