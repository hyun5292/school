package com.spring.ex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		//�� �����ڸ� ���ؼ� ������ �����̳� ����
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		
		//Bean�� student�� ������ ���ο� ��ü student1�� ����
		Student student1 = ctx.getBean("student", Student.class);
		//���
		System.out.println("�̸� : " + student1.getName());
		System.out.println("���� : " + student1.getAge());
		System.out.println("===========================================");
		
		//Bean�� student�� ������ ���ο� ��ü student2�� ����
		Student student2 = ctx.getBean("student", Student.class);
		//student2 �� �Է� - setter
		student2.setName("�ܹ���");
		student2.setAge(3);
		//���
		System.out.println("�̸� : " + student2.getName());
		System.out.println("���� : " + student2.getAge());
		System.out.println("===========================================");
		
		//student1�� student2�� ������ ��ġ�ϴ°�
		if(student1.equals(student2)) {  //��ġ�ϸ�
			//��ġ�� ���
			System.out.println("student1 == student2");
		} else {  //�ƴϸ�
			//��ġ����
			System.out.println("student1 != student2");
		}
		
		/* 
		 * �ٵ� �ڼ��� == �ܹ����� ��ġ�Ѵٰ� ����
		 * => �ϳ��� �����̼� ���µ� �̳������� �� �ٲ���� ������ ����
		 *    singletone���� �ϸ� �װ� ���� �� �־�
		 *    �׷��� student2���� ������ �ٲ㵵 �ȹٲ�°ž�
		 * => �ٵ� scope�� ������ ��ġ�Ѵٰ� ���� �ٵ� �� ���� ���� �;�
		 *    �׷��� singleton���� prototype�� �� �׷� ��
		 * */
		
		ctx.close();  //ctx�� �ݾ���
	}
}
