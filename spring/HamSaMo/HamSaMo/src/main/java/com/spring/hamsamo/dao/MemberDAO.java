package com.spring.hamsamo.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.hamsamo.dto.MemberDTO;

@Repository
public interface MemberDAO {
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
	public MemberDTO MLogin(String mId);

}
