package com.spring.ex;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
   @RequestMapping("/studentForm")
   public String studentForm() {
      return "createPage";
   }
   
   //form���� ��ΰ� /student/create���ڳ� �̰� ����� , BindingResult�� ������ ���� �����
   @RequestMapping("/student/create")
   public String studentCreate(@ModelAttribute("student") Student student, BindingResult result) {
      String page = "createDonePage";
      
      //�� �� validator�� ��ȿ�� �˻���
      StudentValidator validator = new StudentValidator();
      //student �ѰŶ� ��� �Ѱ���
      validator.validate(student, result);
      if(result.hasErrors()) { //������ ������ createPage����
         page = "createPage";
      }
      
      return page;
   }
}