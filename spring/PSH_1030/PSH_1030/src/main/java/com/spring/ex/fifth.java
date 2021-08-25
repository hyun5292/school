package com.spring.ex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class fifth {
	@RequestMapping("/write")
	public String write(Model model) {
		model.addAttribute("university", "경민대학교");
		model.addAttribute("name", "박수현");
		model.addAttribute("age", 22);
		model.addAttribute("animal", "단무쥐");
		
		return "board/write";
	}
}
