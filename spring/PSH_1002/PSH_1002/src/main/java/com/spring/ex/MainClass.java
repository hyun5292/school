package com.spring.ex;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		//생성 - 컨테이너 세팅
		//이 생성자를 통해서 스프링 컨테이너 생성
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		//설정
		ctx.load("classpath:applicationCTX.xml");  //경로인가
		ctx.refresh();  //재활성화랄까?
		//사용 - 빈을 생성하고 가져와
		Student student = ctx.getBean("student", Student.class);
		//출력
		System.out.println("이름 : " + student.getName());
		System.out.println("나이 : " + student.getAge());
		
		ctx.close();  //ctx를 닫아줘
	}
}
