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

	// ���ǳ��� ��������
	@Override
	public List<QnaDTO> GetQList(Map<String, Object> map) {
		return qDAO.GetQList(map);
	}

	// ����ȭ�鿡 ��� 4�� ��������
	@Override
	public List<QnaDTO> GetFour() {
		return qDAO.getFour();
	}

	// �����ϱ� Ŭ�� �� ���� �����ֱ�
	@Override
	public QnaDTO GetQna(int Qnum) {
		return qDAO.GetQna(Qnum);
	}

	// ��ü ������ ���� ���ϱ�
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

	//���ο� ���� �Է�
	@Override
	public boolean NewQna(Map<String, Object> map) {
		return qDAO.NewQna(map);
	}
}
