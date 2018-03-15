package com.xiaopan.lambda._1_simple_examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Package: com.xiaopan.lambda._1_simple_examples
 * @ClassName: _2_Comparator
 * @author: panhuaxiong
 * @date: 2017��8��17�� ����10:15:53
 * @Description: lambda�Ż�Comparator
 */
public class _2_Comparator {
	
	/**
	 * �ο���http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html#section2
	 */
	public static void main(String[] args) {
		System.out.println("comparator Test =======");
		List<Person> personList = createPersonList();
		
		//ʹ�������ڲ����������
		Collections.sort(personList, new Comparator<Person>() {

			@Override
			public int compare(Person p1, Person p2) {
				return p1.getSurName().compareTo(p2.getSurName());
			}
			
		});

		System.out.println("�����ڲ��������� =======");
		for (Person p : personList) {
			p.print();
		}
		
		//ʹ��lambda���ʽ
		personList = createPersonList();
		Collections.sort(personList, (p1, p2) -> p1.getSurName().compareTo(p2.getSurName()));

		System.out.println("lambda���ʽ������ =======");
		for (Person p : personList) {
			p.print();
		}
		
		//ʹ��lambda���ʽ2
		personList = createPersonList();
//		Collections.sort(personList, Comparator.comparing(p -> p.getSurName()));
//		Collections.sort(personList, comparing(p -> p.getSurName()));
		Collections.sort(personList, Comparator.comparing(Person::getSurName));
		
		System.out.println("lambda���ʽ2������ =======");
		personList.forEach(p -> p.print());
		
		
		//ʹ��lamdba���ʽ3
		personList = createPersonList();
		personList.sort(Comparator.comparing(Person::getSurName));
//		personList.sort(comparing(Person::getSurName));
		
		System.out.println("lambda���ʽ3������ =======");
		personList.forEach(p -> p.print());
		System.out.println("lambda���ʽ3������ =======");
		personList.sort(Comparator.comparing(Person::getSurName).reversed());
		personList.forEach(p -> p.print());
	}
	
	private static List<Person> createPersonList() {
		List<Person> list = new ArrayList<Person>();
		list.add(Person.createPerson("xiao", "pan", 12, Gender.MAN, "pan@a.com", "123", "����"));
		list.add(Person.createPerson("xiao", "hua", 12, Gender.WOMAN, "hua@a.com", "321", "�Ϻ�"));
		list.add(Person.createPerson("xiao", "wang", 12, Gender.MAN, "wang@a.com", "213", "����"));
		return list;
	}


	enum Gender {
		MAN, WOMAN;
	}


	static class Person {
		
		public static Person createPerson(String givenName, String surName, int age, Gender gender, String eMail, String phone, String address) {
			Person person = new Person();
			person.setGivenName(givenName);
			person.setSurName(surName);
			person.setAge(age);
			person.setGender(gender);
			person.seteMail(eMail);
			person.setPhone(phone);
			person.setAddress(address);
			return person;
		}
		
		public void print() {
			StringBuilder sb = new StringBuilder();
			sb.append(givenName).append(",");
			sb.append(surName).append(",");
			sb.append(age).append(",");
			sb.append(gender).append(",");
			sb.append(eMail).append(",");
			sb.append(phone).append(",");
			sb.append(address);
			System.out.println(sb.toString());
		}
		
		private String givenName;
		private String surName;
		private int age;
		private Gender gender;
		private String eMail;
		private String phone;
		private String address;
		public String getGivenName() {
			return givenName;
		}
		public void setGivenName(String givenName) {
			this.givenName = givenName;
		}
		public String getSurName() {
			return surName;
		}
		public void setSurName(String surName) {
			this.surName = surName;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public Gender getGender() {
			return gender;
		}
		public void setGender(Gender gender) {
			this.gender = gender;
		}
		public String geteMail() {
			return eMail;
		}
		public void seteMail(String eMail) {
			this.eMail = eMail;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
	}
	
}
