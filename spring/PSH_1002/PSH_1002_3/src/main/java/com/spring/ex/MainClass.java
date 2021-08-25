package com.spring.ex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// �� �����ڸ� ���ؼ� ������ �����̳� ����
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		
		//Bean�� adminConnection�� ������ ���ο� ��ü connection�� ����
		AdminConnection connection = ctx.getBean("adminConnection", AdminConnection.class);
		//���
		System.out.println("adminID : " + connection.getAdminId());
		System.out.println("adminPW : " + connection.getAdminPw());
		System.out.println("sub_adminID : " + connection.getSubAdminId());
		System.out.println("sub_adminPW : " + connection.getSubAdminPw());
		
		ctx.close();  //ctx�� �ݾ���
	}

}
