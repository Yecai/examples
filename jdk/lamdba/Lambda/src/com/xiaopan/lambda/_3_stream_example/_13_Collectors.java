package com.xiaopan.lambda._3_stream_example;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _13_Collectors {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		// groupingBy/partitioningBy
		// 按照年龄归组
		Map<Integer, List<Person>> personGroups = Stream.generate(new PersonSupplier()).limit(100)
				.collect(Collectors.groupingBy(Person::getAge));
		Iterator it = personGroups.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, List<Person>> persons = (Map.Entry) it.next();
			System.out.println("Age " + persons.getKey() + " = " + persons.getValue().size());
		}

		// 按照未成年人和成年人归组
		Map<Boolean, List<Person>> children = Stream.generate(new PersonSupplier()).limit(100)
				.collect(Collectors.partitioningBy(p -> p.getAge() < 18));
		System.out.println("Children number: " + children.get(true).size());
		System.out.println("Adult number: " + children.get(false).size());
	}

	private static class PersonSupplier implements Supplier<Person> {
		private int index = 0;
		private Random random = new Random();

		@Override
		public Person get() {
			return new Person(index++, "StormTestUser" + index, random.nextInt(100));
		}

	}
}
