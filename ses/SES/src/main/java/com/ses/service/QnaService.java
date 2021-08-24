package com.ses.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ses.dao.QnaDAO;
import com.ses.dto.LogDTO;
import com.ses.dto.QnaDTO;
import com.ses.dto.SiteListDTO;

@Service
public class QnaService implements QService {
	@Inject
	QnaDAO qDAO;

	// 문의내역 가져오기
	@Override
	public List<QnaDTO> GetQList(Map<String, Object> map) {
		return qDAO.GetQList(map);
	}

	// 메인화면에 띄울 4개 가져오기
	@Override
	public List<QnaDTO> GetFour() {
		return qDAO.getFour();
	}

	// 문의하기 클릭 시 내용 보여주기
	@Override
	public QnaDTO GetQna(int Qnum) {
		return qDAO.GetQna(Qnum);
	}

	// 전체 페이지 개수 구하기
	@Override
	public int PageCnt() {
		return qDAO.ListCnt();
	}

	@Override
	public int SearchedListCnt(Map<String, Object> map) {
		return qDAO.SearchedListCnt(map);
	}
	
	@Override
	public List<QnaDTO> SearchList(Map<String, Object> map) {
		return qDAO.SearchList(map);
	}

	//새로운 문의 입력
	@Override
	public boolean NewQna(Map<String, Object> map) {
		return qDAO.NewQna(map);
	}
}
