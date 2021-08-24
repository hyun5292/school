package com.ses.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ses.dto.MemberDTO;

public interface MService {

	// �α���
	public MemberDTO MLogin(HttpServletRequest request);

	// �α׾ƿ�
	public void logout(HttpServletResponse response) throws Exception;

	// ���̵� �ߺ� üũ
	public boolean CheckId(String mId);

	// ȸ������
	public boolean MJoin(Map<String, Object> map);

	// IDã��
	public String FindID(Map<String, Object> map);

	// PWã��
	public boolean FindPW(Map<String, Object> map);

	// �ӽú�й�ȣ�� ����
	public void ChangePW(Map<String, Object> map);

	// ȸ������ �������
	public MemberDTO GetInfo(String mID);

	// ȸ������ ����
	public boolean ModifyInfo(Map<String, Object> map);

	// ��й�ȣ ����
	public boolean ChagePWD(Map<String, Object> map);
}
