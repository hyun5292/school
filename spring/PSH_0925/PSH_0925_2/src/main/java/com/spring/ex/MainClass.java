package com.spring.ex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		//이 생성자를 통해서 스프링 컨테이너 생성, 클래스들의 경로를 지정해서 연결
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		//해당 MainClass의 Pencil 클래스 객체인 pencil에 Bean에서 만든 pencil 객체를 배정
		Pencil pencil = ctx.getBean("pencil", Pencil.class);
		pencil.use();  //pencil 인터페이스를 Override한 클래스의 use 메서드 호출
		
		ctx.close();  //ctx를 닫아줘
	}

}
