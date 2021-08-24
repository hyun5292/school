package com.ses.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ses.dao.SiteListDAO;
import com.ses.dto.SiteListDTO;

@Service
public class SiteListService implements SLService {
	@Inject
	SiteListDAO slDAO;

	@Override
	public List<SiteListDTO> GetSLList(Map<String, Object> map) {
		return slDAO.GetSLList(map);
	}

	@Override
	public List<SiteListDTO> SearchList(Map<String, Object> map) {
		return slDAO.SearchList(map);
	}

	// 전체 페이지 개수 구하기
	@Override
	public int PageCnt(Map<String, Object> map) {
		return slDAO.ListCnt(map);
	}

	@Override
	public int SearchedListCnt(Map<String, Object> map) {
		return slDAO.SearchedListCnt(map);
	}

	// 연결 해지
	@Override
	public boolean CancelSNS(Map<String, Object> map) {
		return slDAO.CancelSNS(map);
	}

	// 특정 행 검색
	@Override
	public SiteListDTO SearchOne(Map<String, Object> map) {
		return slDAO.SearchOne(map);
	}

	// 해당 회원의 연결 계정 개수 구하기
	@Override
	public int MemberPageCnt(String mId) {
		return slDAO.MemberPageCnt(mId);
	}
}
