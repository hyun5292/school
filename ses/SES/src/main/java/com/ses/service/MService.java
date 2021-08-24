package com.ses.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ses.dto.MemberDTO;

public interface MService {

	// 로그인
	public MemberDTO MLogin(HttpServletRequest request);

	// 로그아웃
	public void logout(HttpServletResponse response) throws Exception;

	// 아이디 중복 체크
	public boolean CheckId(String mId);

	// 회원가입
	public boolean MJoin(Map<String, Object> map);

	// ID찾기
	public String FindID(Map<String, Object> map);

	// PW찾기
	public boolean FindPW(Map<String, Object> map);

	// 임시비밀번호로 변경
	public void ChangePW(Map<String, Object> map);

	// 회원정보 갖고오기
	public MemberDTO GetInfo(String mID);

	// 회원정보 수정
	public boolean ModifyInfo(Map<String, Object> map);

	// 비밀번호 변경
	public boolean ChagePWD(Map<String, Object> map);
}
