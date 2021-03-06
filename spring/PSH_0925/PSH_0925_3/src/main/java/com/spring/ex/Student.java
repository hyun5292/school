package com.spring.ex;

import java.util.ArrayList;

public class Student {
	//멤버 변수
	private String name;  //이름
	private int age;  //나이
	private ArrayList<String> hobbys;  //취미
	private double height;  //키
	private double weight;  //몸무게
	
	//생성자 매개변수로 값 받아서 초기 변수 값 세팅
	public Student(String name, int age, ArrayList<String> hobbys) {
		//현재 클래스의 멤버 변수 = 매개변수로 받은 값 변수
		this.name = name;
		this.age = age;
		this.hobbys = hobbys;
	}

	//Getter and Setter, 값을 받아서 세팅하고, 다시 값을 돌려주고
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

	public ArrayList<String> getHobbys() {
		return hobbys;
	}

	public void setHobbys(ArrayList<String> hobbys) {
		this.hobbys = hobbys;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
