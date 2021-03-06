package com.spring.hamsamo.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.hamsamo.dto.MemberDTO;

@Repository
public class MemberRunDAO implements MemberDAO{
	@Autowired
	public SqlSessionTemplate temp;
	
	@Inject
	private SqlSession sqlSession;

	// 아이디 중복 체크
	@Override
	public boolean CheckId(String mId) {
		String rs = temp.selectOne("Mmap.MCheckId", mId);
		if (rs == null)
			return true;
		else
			return false;
	}

	// 회원가입
	@Override
	public boolean MJoin(Map<String, Object> map) {
		int rs = temp.insert("Mmap.MJoin", map);
		if (rs == 1)
			return true;
		else
			return false;
	}
	
	// 회원정보수정 화면 보여주기
	@Override
	public MemberDTO GetMInfo(String m_Id) {
		List<MemberDTO> dtos = temp.selectList("Mmap.GetMInfo", m_Id);
		return dtos.get(0);
	}

	// 회원정보수정
	@Override
	public boolean MUpdateInfo(Map<String, Object> map) {
		int rs = temp.update("Mmap.MUpdateInfo", map);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// 회원탈퇴
	@Override
	public boolean MGetOut(String mId) {
		int rs = temp.delete("Mmap.MGetOut", mId);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// 로그인
	@Override
	public MemberDTO MLogin(String mId) {
		return sqlSession.selectOne("Mmap.MLogin", mId);
	}

}
