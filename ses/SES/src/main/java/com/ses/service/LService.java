package com.ses.service;

import java.util.List;
import java.util.Map;

import com.ses.dto.LogDTO;
import com.ses.dto.SiteListDTO;

public interface LService {
	// �Խù� ��� �ҷ�����
	public List<LogDTO> GetLList(Map<String, Object> map);
	
	// �Խù� �˻�
	public List<LogDTO> SearchList(Map<String, Object> map);

	// �˻��� �Խù� ���� ���ϱ�
	public int SearchedListCnt(Map<String, Object> map);

	// ��ü ������ ���� ���ϱ�
	public int PageCnt(Map<String, Object> map);
	
	// ������ ���� �� ���ο� �α�
	public boolean InputLog(Map<String, Object> map);
}
