package com.spring.ex;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Student implements InitializingBean, DisposableBean{
	private String name;
	private int age;
	
	//생성자 - 실행됬을 때 처음 값 초기화 해줄 거
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
	//Bean이 생성될 때 이벤트, 인터페이스였으면 추상메서드
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet()");
	}
	
	@Override
	//Bean이 종료될 때 이벤트, 인터페이스였으면 추상메서드
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("destroy()");
	}
}
