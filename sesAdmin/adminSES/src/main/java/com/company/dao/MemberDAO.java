package com.company.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.MemberDTO;

@Repository
public class MemberDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// list 가져와
	public List<MemberDTO> GetMList(Map<String, Object> map) {
		return temp.selectList("mMap.MList", map);
	}

	// 전체 페이지 개수 구하기
	public int ListCnt() {
		return temp.selectOne("mMap.ListCnt");
	}

<<<<<<< HEAD
=======
	// 일반 회원 수 구하기
	public int GeneralCnt() {
		return temp.selectOne("mMap.GeneralCnt");
	}

	// 탈퇴한 일반 회원 수 구하기
	public int GeneralNotUseCnt() {
		return temp.selectOne("mMap.GeneralNotUseCnt");
	}

>>>>>>> 1f258bbb1ccf1215572a53076409f307eb673dcf
	// 검색된 회원 목록 불러오기
	public List<MemberDTO> GetSearchedMList(Map<String, Object> map) {
		return temp.selectList("mMap.MSearchedList", map);
	}

	// 검색된 페이지 개수 구하기
	public int SearchedListCnt(Map<String, Object> map) {
		return temp.selectOne("mMap.SearchedListCnt", map);
	}
<<<<<<< HEAD
=======

	// 회원의 회원 분류 가져오기
	public String GetMKind(String mId) {
		return temp.selectOne("mMap.GetMKind", mId);
	}

	// 회원 정보 가져오기
	public MemberDTO GetMInfo(String mId) {
		return temp.selectOne("mMap.GetMInfo", mId);
	}
>>>>>>> 1f258bbb1ccf1215572a53076409f307eb673dcf
}