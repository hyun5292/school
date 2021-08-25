package com.spring.ex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

	// first�� ������ �޼��� ���� ���� �� �̽�
	@RequestMapping("/first")
	public String first() {
		// �׶�� first.jsp�� �־�� ����
		return "first";
	}

	// content/second�� ������ �޼��� ���� ���� �� �̽�
	@RequestMapping("/content/second")
	public String second(Model model) {
		// �׶�� second.jsp�� �־�� ����
		model.addAttribute("name", "�ڼ���");
		return "content/second";
	}

	// content/third�� ������ �޼��� ���� ���� �� �̽�
	@RequestMapping("/content/third")
	public String third(Model model) {
		// �׶�� third.jsp�� �־�� ����
		model.addAttribute("name", "�ڼ���");
		return "third";
	}
	// content/fourth�� ������ �޼��� ���� ���� �� �̽�
	@RequestMapping("/content/fourth")
	public ModelAndView fourth() {
		ModelAndView mv = new ModelAndView();  //model�̶� view ���ÿ� �Ѵ�
		mv.addObject("star", "�����ϴ�");  //��ü�� �����͸� ���
		mv.setViewName("content/fourth");  //������ ��� �������ذ� ���⼭ �����
		return mv;
	}
}
