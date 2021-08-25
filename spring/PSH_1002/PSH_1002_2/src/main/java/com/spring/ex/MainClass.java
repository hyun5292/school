package com.spring.ex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		//이 생성자를 통해서 스프링 컨테이너 생성
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		
		//Bean에 student의 정보를 새로운 객체 student1에 배정
		Student student1 = ctx.getBean("student", Student.class);
		//출력
		System.out.println("이름 : " + student1.getName());
		System.out.println("나이 : " + student1.getAge());
		System.out.println("===========================================");
		
		//Bean에 student의 정보를 새로운 객체 student2에 배정
		Student student2 = ctx.getBean("student", Student.class);
		//student2 값 입력 - setter
		student2.setName("단무쥐");
		student2.setAge(3);
		//출력
		System.out.println("이름 : " + student2.getName());
		System.out.println("나이 : " + student2.getAge());
		System.out.println("===========================================");
		
		//student1과 student2의 정보가 일치하는가
		if(student1.equals(student2)) {  //일치하면
			//일치행 출력
			System.out.println("student1 == student2");
		} else {  //아니면
			//일치안행
			System.out.println("student1 != student2");
		}
		
		/* 
		 * 근데 박수현 == 단무쥐라고 일치한다고 나와
		 * => 하나를 여럿이서 쓰는데 이놈저놈이 다 바꿔놔봐 데이터 꼬여
		 *    singletone으로 하면 그걸 막을 수 있어
		 *    그래서 student2에서 정보를 바꿔도 안바뀌는거야
		 * => 근데 scope를 지워도 일치한다고 나와 근데 난 새거 쓰고 싶어
		 *    그러면 singleton말고 prototype을 써 그럼 돼
		 * */
		
		ctx.close();  //ctx를 닫아줘
	}
}
