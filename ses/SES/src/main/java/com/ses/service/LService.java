package com.ses.service;

import java.util.List;
import java.util.Map;

import com.ses.dto.LogDTO;
import com.ses.dto.SiteListDTO;

public interface LService {
	// 게시물 목록 불러오기
	public List<LogDTO> GetLList(Map<String, Object> map);
	
	// 게시물 검색
	public List<LogDTO> SearchList(Map<String, Object> map);

	// 검색된 게시물 개수 구하기
	public int SearchedListCnt(Map<String, Object> map);

	// 전체 페이지 개수 구하기
	public int PageCnt(Map<String, Object> map);
	
	// 간편가입 해지 시 새로운 로그
	public boolean InputLog(Map<String, Object> map);
}
