package com.spring.ex;

public class Family {
	//��� ����
	String papaName;  //�ƺ� �̸�
	String mamaName;  //���� �̸�
	String sisName;  //��� �̸�
	String grandName;  //�ҸӴ� �̸�
	
	//Getter and Setter, ���� �޾Ƽ� �����ϰ�, �ٽ� ���� �����ְ�
	public Family(String papaName, String mamaName) {
		//���� Ŭ������ ��� ���� = �Ű������� ���� �� ����
		this.papaName = papaName;
		this.mamaName = mamaName;
	}

	//Getter and Setter, ���� �޾Ƽ� �����ϰ�, �ٽ� ���� �����ְ�
	public String getPapaName() {
		return papaName;
	}

	public void setPapaName(String papaName) {  //�Ű�����
		this.papaName = papaName;  //���� Ŭ������ ��� ���� = �Ű������� ���� �� ����
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