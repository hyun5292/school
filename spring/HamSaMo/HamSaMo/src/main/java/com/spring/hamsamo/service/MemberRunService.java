package com.spring.hamsamo.service;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.spring.hamsamo.dao.MemberDAO;
import com.spring.hamsamo.dto.MemberDTO;

@Service
public class MemberRunService implements MemberService {
	@Inject
	MemberDAO mDAO;

	// 아이디 중복 체크
	@Override
	public boolean CheckId(String mId) {
		return mDAO.CheckId(mId);
	}

	// 회원가입
	public boolean MJoin(Map<String, Object> map) {
		return mDAO.MJoin(map);
	}

	// 회원정보수정 화면 보여주기
	@Override
	public MemberDTO GetMInfo(String m_Id) {
		return mDAO.GetMInfo(m_Id);
	}

	// 회원정보수정
	@Override
	public boolean MUpdateInfo(Map<String, Object> map) {
		return mDAO.MUpdateInfo(map);
	}

	// 회원탈퇴
	@Override
	public boolean MGetOut(String mId) {
		return mDAO.MGetOut(mId);
	}

	// 로그인
	@Override
	public MemberDTO MLogin(HttpServletRequest request) {
		MemberDTO dto = mDAO.MLogin(request.getParameter("mId"));

		return dto;
	}

	// 로그아웃
	@Override
	public void logout(HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("document.location.href='index';");
		out.println("</script>");
		out.close();
	}
}
