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

	// ��ü ������ ���� ���ϱ�
	@Override
	public int PageCnt(Map<String, Object> map) {
		return slDAO.ListCnt(map);
	}

	@Override
	public int SearchedListCnt(Map<String, Object> map) {
		return slDAO.SearchedListCnt(map);
	}

	// ���� ����
	@Override
	public boolean CancelSNS(Map<String, Object> map) {
		return slDAO.CancelSNS(map);
	}

	// Ư�� �� �˻�
	@Override
	public SiteListDTO SearchOne(Map<String, Object> map) {
		return slDAO.SearchOne(map);
	}

	// �ش� ȸ���� ���� ���� ���� ���ϱ�
	@Override
	public int MemberPageCnt(String mId) {
		return slDAO.MemberPageCnt(mId);
	}
}
