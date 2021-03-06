package com.spring.ex;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//클래스 자체를 어노테이션으로?
@Aspect //공통 기능
public class LogAop {
	@Pointcut("within(com.spring.ex.*)")  //어느 시점인지?
	private void pointcutMethod() {

	}

	@Around("pointcutMethod()")  //Around는 메서드 전후에
	//메서드, 어떤 메서드를 적용시킬건지가 Jointpoint인데 그걸 Parameter로 받는댕
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		// 어떤 메서드가 될거에여~ 신호의 제목을 가져와여
		String signatureStr = joinpoint.getSignature().toShortString();
		// 메서드 이름 is start 출력
		System.out.println(signatureStr + " is start.");
		// Start Time, 현재 시간이자 시작 시간
		long st = System.currentTimeMillis();
		
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
	
	//before 쓰면 실행 전에 이렇게 나온댕
	@Before("within(com.spring.ex.*)")
	public void beforeAdvice() {
		//출력
		System.out.println("beforeAdvice()");
	}
}
