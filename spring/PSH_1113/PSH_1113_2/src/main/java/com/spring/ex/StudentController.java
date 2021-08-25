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
   
   //form에서 경로가 /student/create였자나 이게 실행돼 , BindingResult는 검증에 대한 결과값
   @RequestMapping("/student/create")
   public String studentCreate(@ModelAttribute("student") Student student, BindingResult result) {
      String page = "createDonePage";
      
      //이 때 validator로 유효성 검사해
      StudentValidator validator = new StudentValidator();
      //student 한거랑 결과 넘겨줘
      validator.validate(student, result);
      if(result.hasErrors()) { //에러가 있으면 createPage고대로
         page = "createPage";
      }
      
      return page;
   }
}