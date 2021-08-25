package com.spring.ex;

public class Student {
	//멤버 변수 선언
	private String name;
	private int age;
	private String gradeNum;
	private String classNum;
	
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
	public String getGradeNum() {
		return gradeNum;
	}
	public void setGradeNum(String gradeNum) {
		this.gradeNum = gradeNum;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	
	//학생 정보를 출력
	public void getStudentInfo() {
		System.out.println("이름: " + getName());
		System.out.println("나이: " + getAge());
		System.out.println("학년: " + getGradeNum());
		System.out.println("반: " + getClassNum());
	}
}
