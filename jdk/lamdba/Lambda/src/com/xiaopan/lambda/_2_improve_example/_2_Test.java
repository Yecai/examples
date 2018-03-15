package com.xiaopan.lambda._2_improve_example;

import java.util.List;

public class _2_Test {
	
	public static void main(String[] args) {
		System.out.println("普通实现方式(封装了一下)");
		List<Person> people = Person.createShortList();
		
		_2_RoboContactsMethods rcm = new _2_RoboContactsMethods();
		rcm.callDrivers(people);
		rcm.emailDraftees(people);
		rcm.mailPilots(people);
	}
}
