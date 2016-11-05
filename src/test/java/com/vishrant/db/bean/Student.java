package com.vishrant.db.bean;

import java.util.Arrays;

import com.vishrant.database.annotation.Column;
import com.vishrant.database.annotation.Table;

@Table(name = "student")
public class Student {

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String[] city;

	/*public Student(final String name, final String address) {
		this.name = name;
		this.address = address;
	}*/

	public Student(String name, String address, String[] city) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
	}

	public String getAddress() {
		return this.address;
	}

	public String[] getCity() {
		return this.city;
	}

	public String getName() {
		return this.name;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public void setCity(final String[] city) {
		this.city = city;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [name=" + this.name + ", address=" + this.address + ", city="
				+ Arrays.toString(this.city) + "]";
	}

}
