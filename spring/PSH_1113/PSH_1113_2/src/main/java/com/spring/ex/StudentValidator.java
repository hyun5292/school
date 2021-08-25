package com.spring.ex;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StudentValidator implements Validator {
   @Override
   //������ ��
   public boolean supports(Class<?> arg0) {
      return Student.class.isAssignableFrom(arg0); //������ ��ü�� Ŭ���� Ÿ�� ����
   }
   
   @Override 
   //���������� ����
   public void validate(Object obj, Errors errors) {
      System.out.println("validate()");
      Student student = (Student)obj;
      
      //�̸��� �޾�
      String studentName = student.getName();
      //�ٵ� null�̰ų� ""��
      if(studentName == null || studentName.trim().isEmpty()) {
         //�׷� �̷��� ����ϰ�
    	 System.out.println("studentName is null empty");
         //���� ó���� �̷���
    	 errors.rejectValue("name", "trouble");
      }
      
      //ID�� int������ �޾�
      int studentId = student.getId();
      //�ٵ� 0�̾�
      if(studentId == 0) {
    	  //�׷� �̷��� ����ϰ�
         System.out.println("studentId is 0");
         //���� ó���� �̷���
         errors.rejectValue("id", "trouble");
      }
   }
}