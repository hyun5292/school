package com.spring.ex;

public class Student {
	private String name;
	private int age;
	
	//������ - �������� �� ó�� �� �ʱ�ȭ ���� ��
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	//Getter and Setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
