package com.spring.ex;

public class StudentInfo {  //얘는 자립적으로 생성 못행, 의존적인 관계야, Student 클래스랑 DI 관계야
	private Student student;  //Student 클래스의 객체 선언, private이니까 다른 곳에서는 못써
	
	public StudentInfo(Student student) {  //생성자쓰, 클래스가 돌아가기 전 세팅할 값들을 세팅
		this.student = student;  //현재 클래스의 멤버 객체인 student에 매개변수로 받은 student를 배정
	}
	
	//얘는 getter가 아니야, 반환이 없잖아
	public void getStudentInfo() {  //학생 정보 출력 메서드
		System.out.println("***********************");  //모양이 이래야 이뻐
		if(student != null) {  //현재 student 객체가 null이 아니면, 값을 받아왔다면
			//출력
			System.out.println("이름 : " + student.getName());
			System.out.println("나이 : " + student.getAge());
			System.out.println("학년 : " + student.getGradeNum());
			System.out.println("반 : " + student.getClassNum());
			System.out.println("***********************");
		} else {  //현재 student 객체가 null이면, 값을 받아오지 않았다면
			//출력
			System.out.println("내용이 없자낭");
			System.out.println("***********************");
		}
	}
	
	//근데 얘는 설정해주니까 setter가 맞아
	//매개변수로 Student 형식의 값들을 받아옴
	public void setStudent(Student student) {  
		this.student = student;  //현재 클래스의 멤버 객체인 student에 매개변수로 받은 student를 배정
	}
}
