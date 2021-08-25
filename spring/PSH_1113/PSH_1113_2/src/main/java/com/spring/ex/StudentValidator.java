package com.spring.ex;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StudentValidator implements Validator {
   @Override
   //검증할 거
   public boolean supports(Class<?> arg0) {
      return Student.class.isAssignableFrom(arg0); //검증할 객체와 클래스 타입 정보
   }
   
   @Override 
   //실제적으로 검증
   public void validate(Object obj, Errors errors) {
      System.out.println("validate()");
      Student student = (Student)obj;
      
      //이름을 받아
      String studentName = student.getName();
      //근데 null이거나 ""얌
      if(studentName == null || studentName.trim().isEmpty()) {
         //그럼 이렇게 출력하고
    	 System.out.println("studentName is null empty");
         //에러 처리는 이렇게
    	 errors.rejectValue("name", "trouble");
      }
      
      //ID는 int형으로 받아
      int studentId = student.getId();
      //근데 0이얌
      if(studentId == 0) {
    	  //그럼 이렇게 출력하고
         System.out.println("studentId is 0");
         //에러 처리는 이렇게
         errors.rejectValue("id", "trouble");
      }
   }
}