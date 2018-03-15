package com.xiaopan.lambda._3_stream_example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class _8_sorted {
	public static void main(String[] args) {
		//�� Stream ������ͨ�� sorted ���У���������������ǿ֮��������������ȶ� Stream ���и��� map��filter��limit��skip ���� distinct ������Ԫ�����������������ܰ���������������ִ��ʱ��
		//sorted �ĳɱ��� O(n log n)
		List<Person> persons = new ArrayList<Person>();
		for (int i = 5; i >= 1; i--) {
			Person person = new Person(i, "name" + i);
			persons.add(person);
		}
		List<Person> personList2 = persons.stream().limit(2).sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
				.collect(Collectors.toList());
		personList2.forEach(p -> System.out.println(p.getName()));
	}
}

