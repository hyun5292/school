package com.spring.ex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		//이 생성자를 통해서 스프링 컨테이너 생성, 클래스들의 경로를 지정해서 연결
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		
		//해당 MainClass의 Student 클래스 객체인 student에 Bean에서 만든 Student 객체를 배정
		Student student = ctx.getBean("student", Student.class);
		student.getStudentInfo();  //Student클래스의 메서드 getStudentInfo를 호출
		
		//해당 MainClass의 Worker 클래스 객체인 worker에 Bean에서 만든 Worker 객체를 배정
		Worker worker = ctx.getBean("worker", Worker.class);
		worker.getWorkerInfo();  //Worker클래스의 메서드 getWorkerInfo를 호출
		
		ctx.close();  //열었던 ctx를 닫아줘
	}

}
