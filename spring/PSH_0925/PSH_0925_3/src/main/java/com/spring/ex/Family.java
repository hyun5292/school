package com.spring.ex;

public class Family {
	//멤버 변수
	String papaName;  //아빠 이름
	String mamaName;  //엄마 이름
	String sisName;  //언니 이름
	String grandName;  //할머니 이름
	
	//Getter and Setter, 값을 받아서 세팅하고, 다시 값을 돌려주고
	public Family(String papaName, String mamaName) {
		//현재 클래스의 멤버 변수 = 매개변수로 받은 값 변수
		this.papaName = papaName;
		this.mamaName = mamaName;
	}

	//Getter and Setter, 값을 받아서 세팅하고, 다시 값을 돌려주고
	public String getPapaName() {
		return papaName;
	}

	public void setPapaName(String papaName) {  //매개변수
		this.papaName = papaName;  //현재 클래스의 멤버 변수 = 매개변수로 받은 값 변수
	}

	public String getMamaName() {
		return mamaName;
	}

	public void setMamaName(String mamaName) {
		this.mamaName = mamaName;
	}

	public String getSisName() {
		return sisName;
	}

	public void setSisName(String sisName) {
		this.sisName = sisName;
	}

	public String getGrandName() {
		return grandName;
	}

	public void setGrandName(String grandName) {
		this.grandName = grandName;
	}
	
	
}
