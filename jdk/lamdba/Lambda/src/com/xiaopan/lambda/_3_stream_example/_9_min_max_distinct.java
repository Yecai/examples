package com.xiaopan.lambda._3_stream_example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _9_min_max_distinct {
	public static void main(String[] args) throws IOException {
		// min 和 max 的功能也可以通过对 Stream 元素先sorted排序，再 findFirst 来实现，
		// 但min 和 max的性能会更好，为 O(n)，而 sorted 的成本是 O(n log n)
		//找出最长一行的长度
		BufferedReader br = new BufferedReader(new FileReader("D:\\workspace-self\\works\\jdk\\lamdba\\Lambda\\src\\com\\xiaopan\\lambda\\_3_stream_example\\_9_SUService.log"));
		int longest = br.lines().mapToInt(String::length).max().getAsInt();
		br.close();
		System.out.println(longest);
		
		
		br = new BufferedReader(new FileReader("D:\\workspace-self\\works\\jdk\\lamdba\\Lambda\\src\\com\\xiaopan\\lambda\\_3_stream_example\\_9_SUService.log"));
		//找出全文的单词，转小写，并排序
		List<String> words = br.lines()
				.flatMap(line -> Stream.of(line.split(" ")))
				.filter(word -> word.length() > 0)
				.map(String::toLowerCase)
				.distinct()
				.sorted()
				.collect(Collectors.toList());

		br.close();
		System.out.println(words);
				
	}
}
