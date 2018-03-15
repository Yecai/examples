package com.xiaopan.lambda._2_improve_example;

import java.util.List;

/**
 * @Package: com.xiaopan.lambda._2_improve_example
 * @ClassName: _1_RoboContactsMethods
 * @author: panhuaxiong
 * @date: 2017年8月17日 上午10:14:23
 * @Description: 普通实现方式
 */
public class _1_RoboContactsMethods {

	public void callDrivers(List<Person> people) {
		for (Person person : people) {
			if (person.getAge() >= 16) {
				roboCall(person);
			}
		}
	}
	
	public void emailDraftees(List<Person> people) {
		for (Person person : people) {
			if (person.getAge() >= 18 && person.getAge() <= 25 && person.getGender() == Gender.MALE) {
				roboEmail(person);
			}
		}
	}
	
	public void mailPilots(List<Person> people) {
		for (Person person : people) {
			if (person.getAge() >= 23 && person.getAge() <= 65) {
				roboMail(person);
			}
		}
	}
	
	
	public void roboCall(Person p) {
		System.out.println(
				"Calling " + p.getGivenName() + " " + p.getSurName() + " age " + p.getAge() + " at " + p.getPhone());
	}

	public void roboEmail(Person p) {
		System.out.println(
				"EMailing " + p.getGivenName() + " " + p.getSurName() + " age " + p.getAge() + " at " + p.geteMail());
	}

	public void roboMail(Person p) {
		System.out.println(
				"Mailing " + p.getGivenName() + " " + p.getSurName() + " age " + p.getAge() + " at " + p.getAddress());
	}
}
