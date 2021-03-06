package com.spring.ex;

public class Worker {
	//멤버 변수 선언
	private String name;
	private int age;
	private String job;
	
	//Getter and Setter, 값을 받아서 세팅하고, 다시 값을 돌려주고
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	//일꾼 정보를 출력
	public void getWorkerInfo() {
		System.out.println("이름: " + getName());
		System.out.println("나이: " + getAge());
		System.out.println("직업: " + getJob());
	}
}
