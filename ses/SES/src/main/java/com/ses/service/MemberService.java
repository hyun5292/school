package com.ses.service;

import java.io.PrintWriter;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.ses.dao.MemberDAO;
import com.ses.dto.MemberDTO;

@Service
public class MemberService implements MService {
	@Inject
	MemberDAO mDAO;

	// �α���
	@Override
	public MemberDTO MLogin(HttpServletRequest request) {
		MemberDTO dto = mDAO.MLogin(request.getParameter("M_ID"));

		return dto;
	}

	// �α׾ƿ�
	@Override
	public void logout(HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("document.location.replace('main');");
		out.println("</script>");
		out.close();
	}

	// ���̵� �ߺ� üũ
	@Override
	public boolean CheckId(String mId) {
		return mDAO.CheckId(mId);
	}

	// ȸ������
	@Override
	public boolean MJoin(Map<String, Object> map) {
		return mDAO.MJoin(map);
	}

	// IDã��
	@Override
	public String FindID(Map<String, Object> map) {
		return mDAO.FindID(map);
	}

	// PWã��
	@Override
	public boolean FindPW(Map<String, Object> map) {
		return mDAO.FindPW(map);
	}

	// �ӽú�й�ȣ�� ����
	@Override
	public void ChangePW(Map<String, Object> map) {
		mDAO.ChangePW(map);
	}

	// ȸ������ �������
	@Override
	public MemberDTO GetInfo(String mID) {
		MemberDTO dto = mDAO.MLogin(mID);

		return dto;
	}

	// ȸ������ ����
	@Override
	public boolean ModifyInfo(Map<String, Object> map) {
		return mDAO.ModifyInfo(map);
	}

	// ��й�ȣ ����
	@Override
	public boolean ChagePWD(Map<String, Object> map) {
		return mDAO.ChagePWD(map);
	}
}
