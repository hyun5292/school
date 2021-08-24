package com.ses.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import javax.activation.DataSource;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ses.dto.MemberDTO;

@Repository
public class MemberDAO {
	@Autowired
	public SqlSessionTemplate temp;

	@Inject
	private SqlSession sqlSession;

	// 로그인
	public MemberDTO MLogin(String mId) {
		return sqlSession.selectOne("Mmap.MLogin", mId);
	}

	// 아이디 중복 체크
	public boolean CheckId(String mId) {
		String rs = temp.selectOne("Mmap.MCheckId", mId);
		if (rs == null)
			return true;
		else
			return false;
	}

	// 회원가입
	public boolean MJoin(Map<String, Object> map) {
		int rs = temp.insert("Mmap.MJoin", map);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// ID찾기
	public String FindID(Map<String, Object> map) {
		return temp.selectOne("Mmap.FindID", map);
	}

	// PW찾기
	public boolean FindPW(Map<String, Object> map) {
		String rs = temp.selectOne("Mmap.FindPW", map);
		if (rs == null)
			return true;
		else
			return false;
	}

	// 임시비밀번호로 변경
	public void ChangePW(Map<String, Object> map) {
		temp.update("Mmap.ChgPW", map);
	}

	// 회원정보 수정
	public boolean ModifyInfo(Map<String, Object> map) {
		int rs = temp.update("Mmap.ModifyInfo", map);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// 비밀번호 변경
	public boolean ChagePWD(Map<String, Object> map) {
		int rs = temp.update("Mmap.ChgPW", map);
		if (rs == 1)
			return true;
		else
			return false;
	}
}