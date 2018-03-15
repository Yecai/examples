package com.xiaopan.lambda._3_stream_example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _9_min_max_distinct {
	public static void main(String[] args) throws IOException {
		// min �� max �Ĺ���Ҳ����ͨ���� Stream Ԫ����sorted������ findFirst ��ʵ�֣�
		// ��min �� max�����ܻ���ã�Ϊ O(n)���� sorted �ĳɱ��� O(n log n)
		//�ҳ��һ�еĳ���
		BufferedReader br = new BufferedReader(new FileReader("D:\\workspace-self\\works\\jdk\\lamdba\\Lambda\\src\\com\\xiaopan\\lambda\\_3_stream_example\\_9_SUService.log"));
		int longest = br.lines().mapToInt(String::length).max().getAsInt();
		br.close();
		System.out.println(longest);
		
		
		br = new BufferedReader(new FileReader("D:\\workspace-self\\works\\jdk\\lamdba\\Lambda\\src\\com\\xiaopan\\lambda\\_3_stream_example\\_9_SUService.log"));
		//�ҳ�ȫ�ĵĵ��ʣ�תСд��������
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
