package com.spring.ex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class fifth {
	@RequestMapping("/write")
	public String write(Model model) {
		model.addAttribute("university", "��δ��б�");
		model.addAttribute("name", "�ڼ���");
		model.addAttribute("age", 22);
		model.addAttribute("animal", "�ܹ���");
		
		return "board/write";
	}
}
