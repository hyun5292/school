package com.spring.hamsamo.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.hamsamo.dto.MemberDTO;

public interface MemberService {
	// 아이디 중복 체크
	public boolean CheckId(String mId);

	// 회원가입
	public boolean MJoin(Map<String, Object> map);

	// 회원정보수정 화면 보여주기
	public MemberDTO GetMInfo(String m_Id);

	// 회원정보수정
	public boolean MUpdateInfo(Map<String, Object> map);

	// 회원탈퇴
	public boolean MGetOut(String mId);
	
	// 로그인
	public MemberDTO MLogin(HttpServletRequest request);
	
	// 로그아웃
	public void logout(HttpServletResponse response) throws Exception;
}
