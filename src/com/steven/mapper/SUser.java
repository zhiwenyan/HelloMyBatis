package com.steven.mapper;

import java.util.Date;

public class SUser {
	private int id;
	private String name;
	private Date birthday;
	private double salary;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "SUser [id=" + id + ", name=" + name + ", birthday=" + birthday + ", salary=" + salary + "]";
	}
	
	
}
