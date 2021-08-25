package com.spring.ex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	//CTX 파일이 아무리 많더라도 그냥 다 때려박으면 됑
	public static void main(String[] args) {
		String configLocation1 = "classpath:applicationCTX01.xml"; // 클래스들의 경로를 applicationCTX01로 지정
		String configLocation2 = "classpath:applicationCTX02.xml"; // 클래스들의 경로를 applicationCTX02로  지정
		//이 생성자를 통해서 스프링 컨테이너 생성, 경로 2개 지정(c, p가 이걸 말하는 건가?)
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation1, configLocation2);
		
		//Student1 관련 작업
		Student student1 = ctx.getBean("student1", Student.class); //Bean에 student1의 정보를 새로운 객체 student1에 배정
		System.out.println(student1.getName());  //이름을 출력해주는 getter를 가져와서 출력
		System.out.println(student1.getHobbys());  //취미를 출력해주는 getter를 가져와서 출력
		System.out.println();
		
		//Bean에 student1의 정보를 참조하는 studentInfo를 새로운 객체 student1에 배정
		StudentInfo studentInfo = ctx.getBean("studentInfo1", StudentInfo.class);
		System.out.println();
		
		//Student2 관련 작업
		Student student2 = studentInfo.getStudent();  //위에서 배정된 객체 studentInfo를 student2에 배정
		System.out.println(student2.getName());  //이름을 출력해주는 getter를 가져와서 출력
		System.out.println(student2.getHobbys());  //취미를 출력해주는 getter를 가져와서 출력
		if(student1.equals(student2)) {  //student1과 student2가 같은 사람이면
			System.out.println("student1과 student2는 같은 사람이다.");  //맞다고 출력
		}
		
		//Student3 관련 작업
		Student student3 = ctx.getBean("student3", Student.class); //Bean에 student3의 정보를 새로운 객체 student3에 배정
		System.out.println(student3.getName());  //이름을 출력해주는 getter를 가져와서 출력
		if(student1.equals(student3)) {  //student1과 student3가 같은 사람이면
			System.out.println("student1과 student3은 같은 사람이다.");  //맞다고 출력
		} else {  //다른 사람이면
			System.out.println("student1과 student3은 다른 사람이다.");  //다르다고 출력
		}
		System.out.println();
		
		//Family 관련 작업
		Family family = ctx.getBean("family", Family.class); //Bean에 family의 정보를 새로운 객체 Family에 배정
		System.out.println(family.getPapaName());  //아빠 이름을 출력해주는 getter를 가져와서 출력
		System.out.println(family.getMamaName());  //엄마 이름을 출력해주는 getter를 가져와서 출력
		System.out.println(family.getSisName());  //언니 이름을 출력해주는 getter를 가져와서 출력
		System.out.println(family.getGrandName());  //할머니 이름을 출력해주는 getter를 가져와서 출력
		
		ctx.close();  //ctx를 닫아줘
	}

}
