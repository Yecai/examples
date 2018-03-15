package com.xiaopan.lambda._3_stream_example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class _8_sorted {
	public static void main(String[] args) {
		//对 Stream 的排序通过 sorted 进行，它比数组的排序更强之处在于你可以首先对 Stream 进行各类 map、filter、limit、skip 甚至 distinct 来减少元素数量后，再排序，这能帮助程序明显缩短执行时间
		//sorted 的成本是 O(n log n)
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

