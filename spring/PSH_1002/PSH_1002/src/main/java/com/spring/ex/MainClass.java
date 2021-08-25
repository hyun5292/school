package com.spring.ex;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		//���� - �����̳� ����
		//�� �����ڸ� ���ؼ� ������ �����̳� ����
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		//����
		ctx.load("classpath:applicationCTX.xml");  //����ΰ�
		ctx.refresh();  //��Ȱ��ȭ����?
		//��� - ���� �����ϰ� ������
		Student student = ctx.getBean("student", Student.class);
		//���
		System.out.println("�̸� : " + student.getName());
		System.out.println("���� : " + student.getAge());
		
		ctx.close();  //ctx�� �ݾ���
	}
}
