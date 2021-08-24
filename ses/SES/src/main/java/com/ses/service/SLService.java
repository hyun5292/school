package com.ses.service;

import java.util.List;
import java.util.Map;

import com.ses.dto.SiteListDTO;

public interface SLService {
	// �Խù� ��� �ҷ�����
	public List<SiteListDTO> GetSLList(Map<String, Object> map);

	// �Խù� �˻�
	public List<SiteListDTO> SearchList(Map<String, Object> map);

	// �˻��� �Խù� ���� ���ϱ�
	public int SearchedListCnt(Map<String, Object> map);
	
	// ��ü ������ ���� ���ϱ�
	public int PageCnt(Map<String, Object> map);
	
	// ���� ����
	public boolean CancelSNS(Map<String, Object> map);
	
	// Ư�� �� �˻�
	public SiteListDTO SearchOne(Map<String, Object> map);
	
	// �ش� ȸ���� ���� ���� ���� ���ϱ�
	public int MemberPageCnt(String mId);
}
