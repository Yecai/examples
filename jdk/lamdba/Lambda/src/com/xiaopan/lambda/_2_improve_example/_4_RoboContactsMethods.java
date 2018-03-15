package com.xiaopan.lambda._2_improve_example;

import java.util.List;
import java.util.function.Predicate;

/**
 * @Package: com.xiaopan.lambda._2_improve_example
 * @ClassName: _4_RoboContactsMethods
 * @author: panhuaxiong
 * @date: 2017年8月17日 上午10:14:23
 * @Description: lambda实现方式
 */
public class _4_RoboContactsMethods {

	public void callContacts(List<Person> people, Predicate<Person> test) {
		for (Person person : people) {
			if (test.test(person)) {
				roboCall(person);
			}
		}
	}
	
	public void emailContacts(List<Person> people, Predicate<Person> test) {
		for (Person person : people) {
			if (test.test(person)) {
				roboEmail(person);
			}
		}
	}
	
	public void mailContacts(List<Person> people, Predicate<Person> test) {
		for (Person person : people) {
			if (test.test(person)) {
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
