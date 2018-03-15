package com.xiaopan.lambda._3_stream_example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class _7_limit_skip {
	public static void main(String[] args) {
		
		//limit ���� Stream ��ǰ�� n ��Ԫ�أ�skip �����ӵ�ǰ n ��Ԫ��
//		testLimitSkip();
		
		withSorted();
		
		//��һ�� parallel �� Steam �ܵ���˵�������Ԫ��������ģ���ô limit �����ĳɱ���Ƚϴ���Ϊ���ķ��ض��������ǰ n ��Ҳ��һ�������Ԫ�ء�ȡ����֮�Ĳ�����ȡ��Ԫ�ؼ�Ĵ��򣬻��߲�Ҫ�� parallel Stream��
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
