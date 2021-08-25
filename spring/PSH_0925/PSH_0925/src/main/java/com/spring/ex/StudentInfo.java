package com.spring.ex;

public class StudentInfo {  //��� �ڸ������� ���� ����, �������� �����, Student Ŭ������ DI �����
	private Student student;  //Student Ŭ������ ��ü ����, private�̴ϱ� �ٸ� �������� ����
	
	public StudentInfo(Student student) {  //�����ھ�, Ŭ������ ���ư��� �� ������ ������ ����
		this.student = student;  //���� Ŭ������ ��� ��ü�� student�� �Ű������� ���� student�� ����
	}
	
	//��� getter�� �ƴϾ�, ��ȯ�� ���ݾ�
	public void getStudentInfo() {  //�л� ���� ��� �޼���
		System.out.println("***********************");  //����� �̷��� �̻�
		if(student != null) {  //���� student ��ü�� null�� �ƴϸ�, ���� �޾ƿԴٸ�
			//���
			System.out.println("�̸� : " + student.getName());
			System.out.println("���� : " + student.getAge());
			System.out.println("�г� : " + student.getGradeNum());
			System.out.println("�� : " + student.getClassNum());
			System.out.println("***********************");
		} else {  //���� student ��ü�� null�̸�, ���� �޾ƿ��� �ʾҴٸ�
			//���
			System.out.println("������ ���ڳ�");
			System.out.println("***********************");
		}
	}
	
	//�ٵ� ��� �������ִϱ� setter�� �¾�
	//�Ű������� Student ������ ������ �޾ƿ�
	public void setStudent(Student student) {  
		this.student = student;  //���� Ŭ������ ��� ��ü�� student�� �Ű������� ���� student�� ����
	}
}
