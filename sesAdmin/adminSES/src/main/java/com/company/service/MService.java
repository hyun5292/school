package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.MemberDTO;

public interface MService {
	// 회원 목록 불러오기
	public List<MemberDTO> GetMList(Map<String, Object> map);

	// 전체 페이지 개수 구하기
	public int PageCnt();

	// 검색된 회원 목록 불러오기
	public List<MemberDTO> GetSearchedMList(Map<String, Object> map);
<<<<<<< HEAD

	// 검색된 페이지 개수 구하기
	public int SearchedListCnt(Map<String, Object> map);
=======

	// 검색된 페이지 개수 구하기
	public int SearchedListCnt(Map<String, Object> map);

	// 회원의 회원 분류 가져오기
	public String GetMKind(String mId);

	// 회원 정보 가져오기
	public MemberDTO GetMInfo(String mId);

	// 일반 회원 수 구하기
	public int GeneralCnt();

	// 탈퇴한 일반 회원 수 구하기
	public int GeneralNotUseCnt();
>>>>>>> 1f258bbb1ccf1215572a53076409f307eb673dcf
}
