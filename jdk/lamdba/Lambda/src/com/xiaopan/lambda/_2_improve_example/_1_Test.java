package com.xiaopan.lambda._2_improve_example;

import java.util.List;

public class _1_Test {
	
	public static void main(String[] args) {
		System.out.println("普通实现方式");
		List<Person> people = Person.createShortList();
		
		_1_RoboContactsMethods rcm = new _1_RoboContactsMethods();
		rcm.callDrivers(people);
		rcm.emailDraftees(people);
		rcm.mailPilots(people);
	}
}
