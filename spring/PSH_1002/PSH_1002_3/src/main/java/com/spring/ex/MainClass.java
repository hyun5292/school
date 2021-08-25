package com.spring.ex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// 이 생성자를 통해서 스프링 컨테이너 생성
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		
		//Bean에 adminConnection의 정보를 새로운 객체 connection에 배정
		AdminConnection connection = ctx.getBean("adminConnection", AdminConnection.class);
		//출력
		System.out.println("adminID : " + connection.getAdminId());
		System.out.println("adminPW : " + connection.getAdminPw());
		System.out.println("sub_adminID : " + connection.getSubAdminId());
		System.out.println("sub_adminPW : " + connection.getSubAdminPw());
		
		ctx.close();  //ctx를 닫아줘
	}

}
