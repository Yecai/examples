package com.xiaopan.lambda._2_improve_example;

import java.util.List;
import java.util.function.Predicate;

public class _4_Test {
	
	public static void main(String[] args) {
		System.out.println("lambda实现方式");
		List<Person> people = Person.createShortList();
		
		Predicate<Person> allDrivers = p -> p.getAge() >= 16;
		Predicate<Person> allDraftees = p -> p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == Gender.MALE;
		Predicate<Person> allPilots = p -> p.getAge() >= 23 && p.getAge() <= 65;
		
		_4_RoboContactsMethods rcm = new _4_RoboContactsMethods();
		rcm.callContacts(people, allDrivers);
		rcm.emailContacts(people, allDraftees);
		rcm.mailContacts(people, allPilots);
		
		//组合与匹配变得更加简单
		rcm.mailContacts(people, allDraftees);
		rcm.callContacts(people, allPilots);
	}
}
