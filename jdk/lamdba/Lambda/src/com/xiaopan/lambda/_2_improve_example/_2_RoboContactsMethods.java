package com.xiaopan.lambda._2_improve_example;

import java.util.List;

/**
 * @Package: com.xiaopan.lambda._2_improve_example
 * @ClassName: _2_RoboContactsMethods
 * @author: panhuaxiong
 * @date: 2017年8月17日 上午10:14:23
 * @Description: 普通实现方式(封装了一下)
 */
public class _2_RoboContactsMethods {

	public void callDrivers(List<Person> people) {
		for (Person person : people) {
			if (isDriver(person)) {
				roboCall(person);
			}
		}
	}
	
	public void emailDraftees(List<Person> people) {
		for (Person person : people) {
			if (isDraftee(person)) {
				roboEmail(person);
			}
		}
	}
	
	public void mailPilots(List<Person> people) {
		for (Person person : people) {
			if (isPilot(person)) {
				roboMail(person);
			}
		}
	}
	
	public boolean isDriver(Person p) {
		return p.getAge() >= 16;
	}

	public boolean isDraftee(Person p) {
		return p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == Gender.MALE;
	}

	public boolean isPilot(Person p) {
		return p.getAge() >= 23 && p.getAge() <= 65;
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
