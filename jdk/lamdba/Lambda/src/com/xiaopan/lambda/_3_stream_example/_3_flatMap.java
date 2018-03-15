package com.xiaopan.lambda._3_stream_example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class _3_flatMap {

	public static void main(String[] args) {
		/**
		 * 有一些场景，是一对多映射关系的，这时需要 flatMap
		 */
		Stream<List<Integer>> inputStream = Stream.of(
				Arrays.asList(1),
				Arrays.asList(2,3),
				Arrays.asList(4,5,6)
				);
		//flatMap 把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起，最终 output 的新 Stream 里面已经没有 List 了，都是直接的数字
		Stream<Integer> outputStream = inputStream
				.flatMap((childList) -> childList.stream());
		
		outputStream.forEach(System.out::println);
		
		
	}
}
