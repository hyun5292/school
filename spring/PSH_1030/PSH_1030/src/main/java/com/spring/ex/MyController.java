package com.spring.ex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

	// first로 들어오면 메서드 실행 시켜 이 똬식
	@RequestMapping("/first")
	public String first() {
		// 그라믄 first.jsp가 있어야 겠지
		return "first";
	}

	// content/second로 들어오면 메서드 실행 시켜 이 똬식
	@RequestMapping("/content/second")
	public String second(Model model) {
		// 그라믄 second.jsp가 있어야 겠지
		model.addAttribute("name", "박수돌");
		return "content/second";
	}

	// content/third로 들어오면 메서드 실행 시켜 이 똬식
	@RequestMapping("/content/third")
	public String third(Model model) {
		// 그라믄 third.jsp가 있어야 겠지
		model.addAttribute("name", "박수돌");
		return "third";
	}
	// content/fourth로 들어오면 메서드 실행 시켜 이 똬식
	@RequestMapping("/content/fourth")
	public ModelAndView fourth() {
		ModelAndView mv = new ModelAndView();  //model이랑 view 동시에 한댕
		mv.addObject("star", "없습니다");  //객체에 데이터를 담아
		mv.setViewName("content/fourth");  //위에서 경로 지정해준걸 여기서 해줬어
		return mv;
	}
}
