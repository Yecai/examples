package com.xiaopan.lambda._2_improve_example;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private String givenName;
	private String surName;
	private int age;
	private Gender gender;
	private String eMail;
	private String phone;
	private String address;

	public static List<Person> createShortList() {
		List<Person> people = new ArrayList<Person>();

		people.add(
				new Person.Builder()
				.givenName("Bob")
				.surName("Baker")
				.age(21)
				.gender(Gender.MALE)
				.eMail("bob.baker@example.com")
				.phone("201-121-4678")
				.address("44 4th St, Smallville, KS 12333")
				.build());
		people.add(
		  new Person.Builder()
		        .givenName("Jane")
		        .surName("Doe")
		        .age(25)
		        .gender(Gender.FEMALE)
		        .eMail("jane.doe@example.com")
		        .phone("202-123-4678")
		        .address("33 3rd St, Smallville, KS 12333")
		        .build());
		
		people.add(
		  new Person.Builder()
		        .givenName("John")
		        .surName("Doe")
		        .age(25)
		        .gender(Gender.MALE)
		        .eMail("john.doe@example.com")
		        .phone("202-123-4678")
		        .address("33 3rd St, Smallville, KS 12333")
		        .build());

		return people;
	}

	static class Builder {

		private Person person;

		public Builder() {
			this.person = new Person();
		}

		public Builder givenName(String givenName) {
			this.person.setGivenName(givenName);
			return this;
		};

		public Builder surName(String surName) {
			this.person.setSurName(surName);
			;
			return this;
		};

		public Builder age(int age) {
			this.person.setAge(age);
			;
			return this;
		};

		public Builder gender(Gender gender) {
			this.person.setGender(gender);
			;
			return this;
		};

		public Builder eMail(String eMail) {
			this.person.seteMail(eMail);
			;
			return this;
		};

		public Builder phone(String phone) {
			this.person.setPhone(phone);
			;
			return this;
		};

		public Builder address(String address) {
			this.person.setAddress(address);
			;
			return this;
		};

		public Person build() {
			return this.person;
		}
	}

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

enum Gender {
	MALE, FEMALE;
}
