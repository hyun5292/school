package com.spring.ex;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop {
	//�޼���, � �޼��带 �����ų������ Jointpoint�ε� �װ� Parameter�� �޴´�
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable{
		//� �޼��尡 �ɰſ���~ ��ȣ�� ������ �����Ϳ�
		String signatureStr = joinpoint.getSignature().toShortString();
		//�޼��� �̸� is start ���
		System.out.println(signatureStr + " is start.");
		//Start Time, ���� �ð����� ���� �ð�
		long st =  System.currentTimeMillis();
		
		try {
			//�޾ƿ� �޼��尡 ���� �޼��� ������
			Object obj = joinpoint.proceed();
			return obj;
		} finally {
			//End Time, ���� �ð����� ���� �ð�
			long et = System.currentTimeMillis();
			//�޼��� �̸� is finished ����ϰ� ��� �ð� ���
			System.out.println(signatureStr + " is finished. ");
			System.out.println(signatureStr + " ����ð� : " + (et-st));
		}
	}
}
