package com.spring.ex;

public class Student {
	//멤버 변수 선언, 타입은 private이라 다른 곳에서는 못써
	private String name;  //이름
	private String age;  //나이
	private String gradeNum;  //학년
	private String classNum;  //반
	
	//생성자쓰, 클래스가 돌아가기 전 세팅할 값들을 세팅, default 있는거야
	public Student(String name, String age, String gradeNum, String classNum) {  //매개변수 이름, 나이, 학년, 반
		//현재 클래스의 멤버 변수 = 매개변수로 받은 값 변수
		this.name = name;
		this.age = age;
		this.gradeNum = gradeNum;
		this.classNum = classNum;
	}
	
	//Getter and Setter, 값을 받아서 세팅하고, 다시 값을 돌려주고
	public String getName() {
		return name;
	}
	public void setName(String name) {  //매개변수
		this.name = name;  //현재 클래스의 멤버 변수 = 매개변수로 받은 값 변수
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
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
	
	
}
