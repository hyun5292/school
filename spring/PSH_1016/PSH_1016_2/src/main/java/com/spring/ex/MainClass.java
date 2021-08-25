package com.spring.ex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		//�� �����ڸ� ���ؼ� ������ �����̳� ����, Ŭ�������� ��θ� �����ؼ� ����
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		
		//�ش� MainClass�� Student Ŭ���� ��ü�� student�� Bean���� ���� Student ��ü�� ����
		Student student = ctx.getBean("student", Student.class);
		student.getStudentInfo();  //StudentŬ������ �޼��� getStudentInfo�� ȣ��
		
		//�ش� MainClass�� Worker Ŭ���� ��ü�� worker�� Bean���� ���� Worker ��ü�� ����
		Worker worker = ctx.getBean("worker", Worker.class);
		worker.getWorkerInfo();  //WorkerŬ������ �޼��� getWorkerInfo�� ȣ��
		
		ctx.close();  //������ ctx�� �ݾ���
	}

}