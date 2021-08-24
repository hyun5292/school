package com.ses.service;

import java.util.List;
import java.util.Map;

import com.ses.dto.SiteListDTO;

public interface SLService {
	// 게시물 목록 불러오기
	public List<SiteListDTO> GetSLList(Map<String, Object> map);

	// 게시물 검색
	public List<SiteListDTO> SearchList(Map<String, Object> map);

	// 검색된 게시물 개수 구하기
	public int SearchedListCnt(Map<String, Object> map);
	
	// 전체 페이지 개수 구하기
	public int PageCnt(Map<String, Object> map);
	
	// 연결 해지
	public boolean CancelSNS(Map<String, Object> map);
	
	// 특정 행 검색
	public SiteListDTO SearchOne(Map<String, Object> map);
	
	// 해당 회원의 연결 계정 개수 구하기
	public int MemberPageCnt(String mId);
}
