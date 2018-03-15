package com.xiaopan.lambda._2_improve_example;

import java.util.List;

public class _3_Test {
	
	public static void main(String[] args) {
		System.out.println("匿名内部类实现方式");
		List<Person> people = Person.createShortList();
		
		_3_RoboContactsMethods rcm = new _3_RoboContactsMethods();
		rcm.callContacts(people, new _3_MyTest<Person>() {
			
			@Override
			public boolean test(Person p) {
				return p.getAge() >=16;
			}
		});
		rcm.emailContacts(people, new _3_MyTest<Person>() {
			
			@Override
			public boolean test(Person p) {
				return p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == Gender.MALE;
			}
		});
		rcm.mailContacts(people, new _3_MyTest<Person>() {
			
			@Override
			public boolean test(Person p) {
				return p.getAge() >= 23 && p.getAge() <= 65;
			}
		});
	}
}
