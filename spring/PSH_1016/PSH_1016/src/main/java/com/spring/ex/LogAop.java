package com.spring.ex;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop {
	//메서드, 어떤 메서드를 적용시킬건지가 Jointpoint인데 그걸 Parameter로 받는댕
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable{
		//어떤 메서드가 될거에여~ 신호의 제목을 가져와여
		String signatureStr = joinpoint.getSignature().toShortString();
		//메서드 이름 is start 출력
		System.out.println(signatureStr + " is start.");
		//Start Time, 현재 시간이자 시작 시간
		long st =  System.currentTimeMillis();
		
		try {
			//받아온 메서드가 가진 메서드 실행해
			Object obj = joinpoint.proceed();
			return obj;
		} finally {
			//End Time, 현재 시간이자 끝난 시간
			long et = System.currentTimeMillis();
			//메서드 이름 is finished 출력하고 경과 시간 출력
			System.out.println(signatureStr + " is finished. ");
			System.out.println(signatureStr + " 경과시간 : " + (et-st));
		}
	}
}
