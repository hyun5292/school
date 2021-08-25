package com.spring.ex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	//CTX ������ �ƹ��� ������ �׳� �� ���������� ��
	public static void main(String[] args) {
		String configLocation1 = "classpath:applicationCTX01.xml"; // Ŭ�������� ��θ� applicationCTX01�� ����
		String configLocation2 = "classpath:applicationCTX02.xml"; // Ŭ�������� ��θ� applicationCTX02��  ����
		//�� �����ڸ� ���ؼ� ������ �����̳� ����, ��� 2�� ����(c, p�� �̰� ���ϴ� �ǰ�?)
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation1, configLocation2);
		
		//Student1 ���� �۾�
		Student student1 = ctx.getBean("student1", Student.class); //Bean�� student1�� ������ ���ο� ��ü student1�� ����
		System.out.println(student1.getName());  //�̸��� ������ִ� getter�� �����ͼ� ���
		System.out.println(student1.getHobbys());  //��̸� ������ִ� getter�� �����ͼ� ���
		System.out.println();
		
		//Bean�� student1�� ������ �����ϴ� studentInfo�� ���ο� ��ü student1�� ����
		StudentInfo studentInfo = ctx.getBean("studentInfo1", StudentInfo.class);
		System.out.println();
		
		//Student2 ���� �۾�
		Student student2 = studentInfo.getStudent();  //������ ������ ��ü studentInfo�� student2�� ����
		System.out.println(student2.getName());  //�̸��� ������ִ� getter�� �����ͼ� ���
		System.out.println(student2.getHobbys());  //��̸� ������ִ� getter�� �����ͼ� ���
		if(student1.equals(student2)) {  //student1�� student2�� ���� ����̸�
			System.out.println("student1�� student2�� ���� ����̴�.");  //�´ٰ� ���
		}
		
		//Student3 ���� �۾�
		Student student3 = ctx.getBean("student3", Student.class); //Bean�� student3�� ������ ���ο� ��ü student3�� ����
		System.out.println(student3.getName());  //�̸��� ������ִ� getter�� �����ͼ� ���
		if(student1.equals(student3)) {  //student1�� student3�� ���� ����̸�
			System.out.println("student1�� student3�� ���� ����̴�.");  //�´ٰ� ���
		} else {  //�ٸ� ����̸�
			System.out.println("student1�� student3�� �ٸ� ����̴�.");  //�ٸ��ٰ� ���
		}
		System.out.println();
		
		//Family ���� �۾�
		Family family = ctx.getBean("family", Family.class); //Bean�� family�� ������ ���ο� ��ü Family�� ����
		System.out.println(family.getPapaName());  //�ƺ� �̸��� ������ִ� getter�� �����ͼ� ���
		System.out.println(family.getMamaName());  //���� �̸��� ������ִ� getter�� �����ͼ� ���
		System.out.println(family.getSisName());  //��� �̸��� ������ִ� getter�� �����ͼ� ���
		System.out.println(family.getGrandName());  //�ҸӴ� �̸��� ������ִ� getter�� �����ͼ� ���
		
		ctx.close();  //ctx�� �ݾ���
	}

}
