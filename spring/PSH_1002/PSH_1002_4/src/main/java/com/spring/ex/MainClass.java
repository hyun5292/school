package com.spring.ex;

import java.util.Scanner;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		String config = null;  //config?
		Scanner scanner = new Scanner(System.in);
		//String으로 값을 입력받아
		String str = scanner.next();
		if(str.equals("dev")) {  //dev면
			config = "dev";  //config에 dev 넣어줘
		} else if(str.equals("run")) {  //run이면
			config = "run";  //config에 run 넣어줘
		}
		
		scanner.close();  //scanner 닫아줘
		
		//생성
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		//ctx 환경 가져와서 프로파일 config를 통해서 활성화 시켜줘
		ctx.getEnvironment().setActiveProfiles(config);
		ctx.load("classpath:applicationCTX_dev.xml", "applicationCTX_run.xml");
		
		//Bean에 serverInfo의 정보를 새로운 객체 info에 배정
		ServerInfo info = ctx.getBean("serverInfo", ServerInfo.class);
		//출력
		System.out.println("ip : " + info.getIpNum());
		System.out.println("port : " + info.getPortNum());
		
		ctx.close();  //ctx 닫아줘
	}
}
