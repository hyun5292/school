package com.ses.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ses.dto.LogDTO;
import com.ses.dto.QnaDTO;
import com.ses.dto.SiteListDTO;

@Service
public interface QService {
	// �Խù� ��� �ҷ�����
	public List<QnaDTO> GetQList(Map<String, Object> map);

	// ����ȭ�鿡 ��� 4�� ��������
	public List<QnaDTO> GetFour();

	// �����ϱ� Ŭ�� �� ���� �����ֱ�
	public QnaDTO GetQna(int Qnum);

	// ��ü ������ ���� ���ϱ�
	public int PageCnt();

	// �˻��� �Խù� ���� ���ϱ�
	public int SearchedListCnt(Map<String, Object> map);

	// �Խù� �˻�
	public List<QnaDTO> SearchList(Map<String, Object> map);
	
	//���ο� ���� �Է�
	public boolean NewQna(Map<String, Object> map);
}
