package com.spring.ex;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.addClass.Member;

@Controller
public class MyController {
	@RequestMapping("board/confirmId") // board/confirmId�� ���ؼ� �����Ѵ�
	// confirmId�� ���ư��µ� ����ڰ� httpServletRequest�� �Ѱ��ٰž�
	public String confirmId(HttpServletRequest httpServletRequest, Model model) {
		// �� �� id��� parameter�� id�� ��� pw��� parameter�� pw�� ���
		String id = httpServletRequest.getParameter("id");
		String pw = httpServletRequest.getParameter("pw");
		// �׸��� �װ� model�� ������
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		// �׸��� board/confirmId -> viewer�� �������ذž�
		return "board/confirmId";
	}

	@RequestMapping("board/checkId") // board/checkId�� ���ؼ� �����Ѵ�
	// checkId�� ���ư��µ� ������ �ٸ��� @RequestParam("id") String id�� �ϸ� �ٷ� String������ id�� ����ش�
	public String checkId(@RequestParam("id") String id, @RequestParam("pw") int pw, Model model) {
		// �׸��� �װ� model�� ������
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		// �׸��� board/checkId -> viewer�� �������ذž�
		return "board/checkId";
	}
	/*
	@RequestMapping("member/join") // member/join�� ���ؼ� �����Ѵ�
	// joinData�� ���ư��µ� ������ �ٸ��� @RequestParam("name") String name�� �ϸ� �ٷ� String������ name�� ����ش�
	public String joinData(@RequestParam("name") String name, @RequestParam("id") String id, @RequestParam("pw") String pw, @RequestParam("email") String email, Model model) {
		//Member Ŭ���� ��ü �����ؼ�
		Member member = new Member();
		//getter&setter�� �־���
		member.setName(name);
		member.setId(id);
		member.setPw(pw);
		member.setEmail(email);
		// �׸��� member ��ü ����� model�� ������
		model.addAttribute("member", member);

		// �׸��� member/join -> viewer�� �������ذž�
		return "member/join";
	}
	*/
	//Command ��ü
	@RequestMapping("member/join")
	public String joinData(Member member) {
		return "member/join";
	}
	
	
	//��ο��ٰ� ������ ���ָ� �� name = ���ñ� �̷��� �ʿ� ����
	@RequestMapping("/student/{studentName}")
	public String getStudent(@PathVariable String studentName, Model model) {
		model.addAttribute("studentName", studentName);
		return "student/studentView";
	}
}
