package com.ses.service;

import java.io.PrintWriter;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.ses.dao.MemberDAO;
import com.ses.dto.MemberDTO;

@Service
public class MemberService implements MService {
	@Inject
	MemberDAO mDAO;

	// 로그인
	@Override
	public MemberDTO MLogin(HttpServletRequest request) {
		MemberDTO dto = mDAO.MLogin(request.getParameter("M_ID"));

		return dto;
	}

	// 로그아웃
	@Override
	public void logout(HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("document.location.replace('main');");
		out.println("</script>");
		out.close();
	}

	// 아이디 중복 체크
	@Override
	public boolean CheckId(String mId) {
		return mDAO.CheckId(mId);
	}

	// 회원가입
	@Override
	public boolean MJoin(Map<String, Object> map) {
		return mDAO.MJoin(map);
	}

	// ID찾기
	@Override
	public String FindID(Map<String, Object> map) {
		return mDAO.FindID(map);
	}

	// PW찾기
	@Override
	public boolean FindPW(Map<String, Object> map) {
		return mDAO.FindPW(map);
	}

	// 임시비밀번호로 변경
	@Override
	public void ChangePW(Map<String, Object> map) {
		mDAO.ChangePW(map);
	}

	// 회원정보 갖고오기
	@Override
	public MemberDTO GetInfo(String mID) {
		MemberDTO dto = mDAO.MLogin(mID);

		return dto;
	}

	// 회원정보 수정
	@Override
	public boolean ModifyInfo(Map<String, Object> map) {
		return mDAO.ModifyInfo(map);
	}

	// 비밀번호 변경
	@Override
	public boolean ChagePWD(Map<String, Object> map) {
		return mDAO.ChagePWD(map);
	}
}
