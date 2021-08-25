package com.spring.ex;

public class Student {
	//��� ���� ����, Ÿ���� private�̶� �ٸ� �������� ����
	private String name;  //�̸�
	private String age;  //����
	private String gradeNum;  //�г�
	private String classNum;  //��
	
	//�����ھ�, Ŭ������ ���ư��� �� ������ ������ ����, default �ִ°ž�
	public Student(String name, String age, String gradeNum, String classNum) {  //�Ű����� �̸�, ����, �г�, ��
		//���� Ŭ������ ��� ���� = �Ű������� ���� �� ����
		this.name = name;
		this.age = age;
		this.gradeNum = gradeNum;
		this.classNum = classNum;
	}
	
	//Getter and Setter, ���� �޾Ƽ� �����ϰ�, �ٽ� ���� �����ְ�
	public String getName() {
		return name;
	}
	public void setName(String name) {  //�Ű�����
		this.name = name;  //���� Ŭ������ ��� ���� = �Ű������� ���� �� ����
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGradeNum() {
		return gradeNum;
	}
	public void setGradeNum(String gradeNum) {
		this.gradeNum = gradeNum;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	
	
}
