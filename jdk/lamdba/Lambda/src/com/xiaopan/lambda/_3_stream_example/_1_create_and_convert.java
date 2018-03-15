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
 * @date: 2017��8��17�� ����2:40:10
 * @Description: ���Ĺ�����ת��
 * https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/
 */
public class _1_create_and_convert {
	@SuppressWarnings({ "unused", "rawtypes" })
	public static void main(String[] args) {
		
		//�Ӷ������ݴ���
		Stream<String> stream = Stream.of("a", "b", "c");
		
		//ͨ��Arrays����
		String[] strArray = new String[] {"a", "b", "c"};
		stream = Stream.of(strArray);
		stream = Arrays.stream(strArray);
		
		//Collection
		List<String> list = Arrays.asList(strArray);
		stream = list.stream();
		
		//����������IntStream��LongStream��DoubleStream
		IntStream.of(new int[] {1,2,3}).forEach(System.out::println);
		IntStream.range(1, 3).forEach(System.out::println);
		IntStream.rangeClosed(1, 3).forEach(System.out::println);
		
		//��ת��Ϊ�������ݽṹ
		//ת��Ϊ����
		String[] strArray1 = stream.toArray(String[]::new);
		//ת��ΪCollection
		List<String> list1 = stream.collect(Collectors.toList());
		List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
		Set set1 = stream.collect(Collectors.toSet());
		Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
		//ת��Ϊstring
		String str = stream.collect(Collectors.joining()).toString();
		
		
	}
}
