package com.spring.hamsamo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.CommandMap;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.hamsamo.dto.MemberDTO;
import com.spring.hamsamo.service.BoardService;
import com.spring.hamsamo.service.MemberService;

@Controller
@Repository(value = "/member")
public class MemberController {
	@Autowired
	MemberService mService;
	@Autowired
	BoardService bService;

	@Inject
	HttpSession session;

	// 로그인 폼 보여주기
	@RequestMapping("/loginForm")
	public String MLoginForm(Model model) {
		System.out.println("Controller - LoginForm()");

		return "/member/loginForm";
	}

	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String MLogin(HttpServletResponse response, HttpServletRequest request, RedirectAttributes attr)
			throws Exception {
		System.out.println("Controller - Login()");
		MemberDTO dto = mService.MLogin(request);

		if (dto == null) { // 해당 회원이 존재하지 않을 경우 
			session.setAttribute("mId", null);
			session.setAttribute("mPw", null);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('존재하지 않는 회원입니다!!'); document.location.href='loginForm';</script>");
			out.flush();
		} else if (!dto.getmPw().equals(request.getParameter("mPw"))) { // 비밀번호가 일치하지 않을 경우
			session.setAttribute("mId", null);
			session.setAttribute("mPw", null);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호가 틀렸습니다!!'); document.location.href='loginForm';</script>");
			out.flush();
		} else { // 성공
			session.setAttribute("mId", dto.getmId());
			session.setAttribute("mPw", dto.getmPw());
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('환영합니다!!'); document.location.href='list'</script>");
			out.flush();
			return "list";
		}

		return "/member/loginForm";
	}

	// 로그아웃
	@RequestMapping(value = "/Logout")
	public String MLogout(HttpServletResponse response, HttpServletRequest request, RedirectAttributes attr)
			throws Exception {
		System.out.println("Controller - Logout()");

		session.removeAttribute("user");
		session.invalidate();
		mService.logout(response);

		return "index";
	}

	// 회원가입 폼 보여주기
	@RequestMapping("/joinForm")
	public String MJoinForm(Model model) {
		System.out.println("Controller - joinForm()");
		// 작성 날짜 넣으려고 timestamp 작성한 거
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String max = (ts.getYear() + 1900) - 18 + "-12-31";
		String min = (ts.getYear() + 1900) - 101 + "-01-01";

		model.addAttribute("max", max);
		model.addAttribute("min", min);

		return "/member/joinForm";
	}

	// 댓글 폼 보여주기
	@RequestMapping("/join")
	public String MJoin(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		System.out.println("Controller - joinForm()");
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("mId", request.getParameter("mId"));
		map.put("mName", request.getParameter("mName"));
		map.put("mPw", request.getParameter("mPw"));
		map.put("mPwChk", request.getParameter("mPwChk"));
		map.put("mBirth", request.getParameter("mBirth"));
		map.put("mTel1", request.getParameter("mTel1"));
		map.put("mTel2", request.getParameter("mTel2"));
		map.put("mTel3", request.getParameter("mTel3"));

		boolean chkId = mService.CheckId(request.getParameter("mId"));

		if (chkId) {
			boolean chkJoin = mService.MJoin(map);
			if (chkJoin) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('가입되었습니다'); document.location.href='loginForm'</script>");
				out.flush();
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
				out.flush();
			}
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('아이디가 중복되었습니다'); history.go(-1);</script>");
			out.flush();
		}
		return "/member/loginForm";
	}

	// 회원정보수정 화면
	@RequestMapping("/MmodifyForm")
	public String MModifyForm(HttpServletRequest request, Model model) {
		System.out.println("Controller - ModifyForm()");

		MemberDTO dto = mService.GetMInfo(session.getAttribute("mId").toString());
		dto.setmBirth(dto.getmBirth().substring(0, 10));

		model.addAttribute("dto", dto);

		return "/member/MmodifyForm";
	}

	// 회원정보수정 화면
	@RequestMapping("/Mmodify")
	public String MModify(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		System.out.println("Controller - Modify()");
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("mId", request.getParameter("mId"));
		map.put("mName", request.getParameter("mName"));
		map.put("mTel1", request.getParameter("mTel1"));
		map.put("mTel2", request.getParameter("mTel2"));
		map.put("mTel3", request.getParameter("mTel3"));

		boolean chk = mService.MUpdateInfo(map);

		if (chk) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원정보가 수정되었습니다'); document.location.href='list'</script>");
			out.flush();
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
			out.flush();
		}

		return "list";
	}

	// 회원탈퇴 화면
	@RequestMapping("/GetOutForm")
	public String MGetOutForm(Model model) {
		System.out.println("Controller - GetOutForm()");

		return "/member/GetOutForm";
	}

	// 회원탈퇴
	@RequestMapping("/getout")
	public String MGetOut(HttpServletResponse response, Model model) throws IOException {
		System.out.println("Controller - GetOut()");
		
		boolean chk = mService.MGetOut(session.getAttribute("mId").toString());
		
		if (chk) {
			bService.UpdateMember(session.getAttribute("mId").toString());
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('탈퇴되었습니다'); document.location.href='index'</script>");
			out.flush();
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
			out.flush();
		}
		
		return "/index";
	}
}
