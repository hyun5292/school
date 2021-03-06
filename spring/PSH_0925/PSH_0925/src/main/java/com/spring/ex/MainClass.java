package com.spring.ex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		String configLocation = "classpath:applicationCTX.xml"; // 클래스들의 경로를 지정
		//이 생성자를 통해서 스프링 컨테이너 생성
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		//Bean에 student1의 정보를 새로운 객체 studentInfo에 배정
		//스프링 컨테이너에서 컴포넌트 가져옴
		//MainClass에는 studentInfo라는 객체가 없자나 그니까 만들어 준거야, MainClass의 studentInfo != applicationCTX의 studentInfo
		StudentInfo studentInfo = ctx.getBean("studentInfo", StudentInfo.class);
		studentInfo.getStudentInfo();  //해당 학생, Bean에 student1의 정보를 출력해주는 메서드 호출
		
		//Bean에 student2의 정보를 새로운 객체 student2에 배정
		Student student2 = ctx.getBean("student2", Student.class);
		studentInfo.setStudent(student2);  //student2에 배정해 준 값들을 세팅해주는 setter 호출
		studentInfo.getStudentInfo();  //해당 학생, Bean에 student2의 정보를 출력해주는 메서드 호출
		
		ctx.close();  //ctx를 닫아줘
	}

}
