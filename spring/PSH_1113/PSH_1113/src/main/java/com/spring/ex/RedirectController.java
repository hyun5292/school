package com.spring.ex;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
	@RequestMapping("/studentConfirm")
	public String studentRedirect(HttpServletRequest httpServletRequest, Model model) {
		String id = httpServletRequest.getParameter("id");
		if(id.equals("abcd")) return "redirect:studentOk";
		return "redirect:studentNg";
		//redirect:뭐시기 쓰면 경로고 뭐고 뭔지 모르겠고 어딨는지 모르겠고 그냥 ng로 바로 가
	}
	
	@RequestMapping("/studentOk")
	public String studentOk(Model model) {
		return "student/studentOk";
	}
	
	@RequestMapping("/studentNg")
	public String studentNg(Model model) {
		return "student/studentNg";
	}
	
	@RequestMapping("/studentURL1")
	public String studentURL1(Model model) {
		return "redirect:http://localhost:8181/ex/studentURL1.jsp";
	}
	
	@RequestMapping("/studentURL2")
	public String studentURL2(Model model) {
		return "redirect:student/studentURL2";
		//webapp 밑에 있는데? 멀라 걍 studentURL2로 찾아가
	}
	
	@RequestMapping("/student/studentURL2")
	public String studentURL22(Model model) {
		return "redirect:http://localhost:8181/ex/studentURL2.jsp";
	}
}
