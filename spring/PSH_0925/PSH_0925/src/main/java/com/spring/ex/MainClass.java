package com.spring.ex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		String configLocation = "classpath:applicationCTX.xml"; // Ŭ�������� ��θ� ����
		//�� �����ڸ� ���ؼ� ������ �����̳� ����
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		//Bean�� student1�� ������ ���ο� ��ü studentInfo�� ����
		//������ �����̳ʿ��� ������Ʈ ������
		//MainClass���� studentInfo��� ��ü�� ���ڳ� �״ϱ� ����� �ذž�, MainClass�� studentInfo != applicationCTX�� studentInfo
		StudentInfo studentInfo = ctx.getBean("studentInfo", StudentInfo.class);
		studentInfo.getStudentInfo();  //�ش� �л�, Bean�� student1�� ������ ������ִ� �޼��� ȣ��
		
		//Bean�� student2�� ������ ���ο� ��ü student2�� ����
		Student student2 = ctx.getBean("student2", Student.class);
		studentInfo.setStudent(student2);  //student2�� ������ �� ������ �������ִ� setter ȣ��
		studentInfo.getStudentInfo();  //�ش� �л�, Bean�� student2�� ������ ������ִ� �޼��� ȣ��
		
		ctx.close();  //ctx�� �ݾ���
	}

}