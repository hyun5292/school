package com.spring.ex;

public class Calculator {
	public void addition(int f, int s) {
		System.out.println("addition");
		int result = f + s;
		System.out.println(f + "+" + s + "=" + result);
		System.out.println();
	}
	
	public void substraction(int f, int s) {
		System.out.println("substraction");
		int result = f - s;
		System.out.println(f + "-" + s + "=" + result);
		System.out.println();
	}
	
	public void multiplication(int f, int s) {
		System.out.println("multiplication");
		int result = f * s;
		System.out.println(f + "*" + s + "=" + result);
		System.out.println();
	}
	
	public void division(int f, int s) {
		System.out.println("division");
		if(s == 0) {
			System.out.println("�׷�����...");
			System.out.println();
		}
		else {
			int result = f / s;
			System.out.println(f + "/" + s + "=" + result);
			System.out.println();
		}
	}
}
