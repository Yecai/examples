package com.xiaopan.lambda._3_stream_example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class _7_limit_skip {
	public static void main(String[] args) {
		
		//limit 返回 Stream 的前面 n 个元素；skip 则是扔掉前 n 个元素
//		testLimitSkip();
		
		withSorted();
		
		//对一个 parallel 的 Steam 管道来说，如果其元素是有序的，那么 limit 操作的成本会比较大，因为它的返回对象必须是前 n 个也有一样次序的元素。取而代之的策略是取消元素间的次序，或者不要用 parallel Stream。
	}

	private static void withSorted() {
		List<Person> persons = new ArrayList<Person>();
		for (int i = 5; i >= 1; i--) {
			Person person = new Person(i, "name" + i);
			persons.add(person);
		}
		List<Person> personList2 = persons.stream()
				.peek(p -> System.out.println("beforeSorted:" + p.getName()))
				.sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
				.peek(p -> System.out.println("afterSorted:" + p.getName()))
				.skip(2).limit(2)
				.collect(Collectors.toList());
//		System.out.println(personList2);
		personList2.forEach(p -> System.out.println(p.getName()));
	}

	private static void testLimitSkip() {
		List<Person> persons   = new ArrayList<Person>();
		for (int i = 1; i < 10000; i++) {
			Person person = new Person(i, "name" + i);
			persons.add(person);
		}
		
		List<String> persons2 = persons.stream()
				.map(Person::getName).limit(10).skip(3).collect(Collectors.toList());
//		persons2.forEach(System.out::println);
		System.out.println(persons2);
	}
}
class Person {
	private int id;
	private String name;
	private int age;
	
	public Person(int i , String name) {
		this(i, name, 0);
	}
	public Person(int i, String name, int age) {
		this.id = i;
		this.name = name;
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
