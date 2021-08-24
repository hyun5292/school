package com.ses.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ses.dto.LogDTO;
import com.ses.dto.QnaDTO;
import com.ses.dto.SiteListDTO;

@Service
public interface QService {
	// 게시물 목록 불러오기
	public List<QnaDTO> GetQList(Map<String, Object> map);

	// 메인화면에 띄울 4개 가져오기
	public List<QnaDTO> GetFour();

	// 문의하기 클릭 시 내용 보여주기
	public QnaDTO GetQna(int Qnum);

	// 전체 페이지 개수 구하기
	public int PageCnt();

	// 검색된 게시물 개수 구하기
	public int SearchedListCnt(Map<String, Object> map);

	// 게시물 검색
	public List<QnaDTO> SearchList(Map<String, Object> map);
	
	//새로운 문의 입력
	public boolean NewQna(Map<String, Object> map);
}
