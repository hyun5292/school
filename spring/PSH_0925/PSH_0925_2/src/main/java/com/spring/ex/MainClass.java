package com.spring.ex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		//�� �����ڸ� ���ؼ� ������ �����̳� ����, Ŭ�������� ��θ� �����ؼ� ����
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		//�ش� MainClass�� Pencil Ŭ���� ��ü�� pencil�� Bean���� ���� pencil ��ü�� ����
		Pencil pencil = ctx.getBean("pencil", Pencil.class);
		pencil.use();  //pencil �������̽��� Override�� Ŭ������ use �޼��� ȣ��
		
		ctx.close();  //ctx�� �ݾ���
	}

}
