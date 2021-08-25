package com.spring.ex;

import java.util.Scanner;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		String config = null;  //config?
		Scanner scanner = new Scanner(System.in);
		//String���� ���� �Է¹޾�
		String str = scanner.next();
		if(str.equals("dev")) {  //dev��
			config = "dev";  //config�� dev �־���
		} else if(str.equals("run")) {  //run�̸�
			config = "run";  //config�� run �־���
		}
		
		scanner.close();  //scanner �ݾ���
		
		//����
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		//ctx ȯ�� �����ͼ� �������� config�� ���ؼ� Ȱ��ȭ ������
		ctx.getEnvironment().setActiveProfiles(config);
		ctx.load("classpath:applicationCTX_dev.xml", "applicationCTX_run.xml");
		
		//Bean�� serverInfo�� ������ ���ο� ��ü info�� ����
		ServerInfo info = ctx.getBean("serverInfo", ServerInfo.class);
		//���
		System.out.println("ip : " + info.getIpNum());
		System.out.println("port : " + info.getPortNum());
		
		ctx.close();  //ctx �ݾ���
	}
}
