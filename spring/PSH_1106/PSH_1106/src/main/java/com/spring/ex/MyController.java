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
	@RequestMapping("board/confirmId") // board/confirmId를 통해서 매핑한댕
	// confirmId가 돌아가는데 사용자가 httpServletRequest로 넘겨줄거야
	public String confirmId(HttpServletRequest httpServletRequest, Model model) {
		// 이 때 id라는 parameter를 id에 담고 pw라는 parameter를 pw에 담고
		String id = httpServletRequest.getParameter("id");
		String pw = httpServletRequest.getParameter("pw");
		// 그리고 그걸 model에 담아줬어
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		// 그리고 board/confirmId -> viewer를 설정해준거야
		return "board/confirmId";
	}

	@RequestMapping("board/checkId") // board/checkId를 통해서 매핑한댕
	// checkId가 돌아가는데 위에랑 다르게 @RequestParam("id") String id로 하면 바로 String형태의 id에 담아준대
	public String checkId(@RequestParam("id") String id, @RequestParam("pw") int pw, Model model) {
		// 그리고 그걸 model에 담아줬어
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		// 그리고 board/checkId -> viewer를 설정해준거야
		return "board/checkId";
	}
	/*
	@RequestMapping("member/join") // member/join를 통해서 매핑한댕
	// joinData가 돌아가는데 위에랑 다르게 @RequestParam("name") String name로 하면 바로 String형태의 name에 담아준대
	public String joinData(@RequestParam("name") String name, @RequestParam("id") String id, @RequestParam("pw") String pw, @RequestParam("email") String email, Model model) {
		//Member 클래스 객체 생성해서
		Member member = new Member();
		//getter&setter로 넣어줘
		member.setName(name);
		member.setId(id);
		member.setPw(pw);
		member.setEmail(email);
		// 그리고 member 객체 만든걸 model에 담아줬어
		model.addAttribute("member", member);

		// 그리고 member/join -> viewer를 설정해준거야
		return "member/join";
	}
	*/
	//Command 객체
	@RequestMapping("member/join")
	public String joinData(Member member) {
		return "member/join";
	}
	
	
	//경로에다가 변수를 써주면 됑 name = 뭐시기 이런거 필요 없음
	@RequestMapping("/student/{studentName}")
	public String getStudent(@PathVariable String studentName, Model model) {
		model.addAttribute("studentName", studentName);
		return "student/studentView";
	}
}
