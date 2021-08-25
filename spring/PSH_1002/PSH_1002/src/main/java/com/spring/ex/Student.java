package com.spring.ex;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Student implements InitializingBean, DisposableBean{
	private String name;
	private int age;
	
	//������ - �������� �� ó�� �� �ʱ�ȭ ���� ��
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	//getter
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}

	@Override
	//Bean�� ������ �� �̺�Ʈ, �������̽������� �߻�޼���
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet()");
	}
	
	@Override
	//Bean�� ����� �� �̺�Ʈ, �������̽������� �߻�޼���
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("destroy()");
	}
}
