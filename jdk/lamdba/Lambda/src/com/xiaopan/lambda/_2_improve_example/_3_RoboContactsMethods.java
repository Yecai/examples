package com.xiaopan.lambda._2_improve_example;

import java.util.List;

/**
 * @Package: com.xiaopan.lambda._2_improve_example
 * @ClassName: _3_RoboContactsMethods
 * @author: panhuaxiong
 * @date: 2017年8月17日 上午10:14:23
 * @Description: 匿名内部类实现方式
 */
public class _3_RoboContactsMethods {

	public void callContacts(List<Person> people, _3_MyTest<Person> test) {
		for (Person person : people) {
			if (test.test(person)) {
				roboCall(person);
			}
		}
	}
	
	public void emailContacts(List<Person> people, _3_MyTest<Person> test) {
		for (Person person : people) {
			if (test.test(person)) {
				roboEmail(person);
			}
		}
	}
	
	public void mailContacts(List<Person> people, _3_MyTest<Person> test) {
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
