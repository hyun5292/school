package com.spring.ex;

public class StudentInfo {
	//멤버 변수
	private Student student;  //Student 객체
	
	public StudentInfo() {
		
	}
	
	//Getter and Setter, 값을 받아서 세팅하고, 다시 값을 돌려주고
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
}
