package com.ses.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ses.dto.MemberDTO;
import com.ses.service.MemberService;

@Controller
@Repository
public class MemberController {
	@Autowired
	MemberService mService;

	@Inject
	HttpSession session;

	// �α���
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String MLogin(HttpServletResponse response, HttpServletRequest request, RedirectAttributes attr)
			throws Exception {
		System.out.println("Controller - Login()");
		MemberDTO dto = mService.MLogin(request);

		if (dto == null) { // �ش� ȸ���� �������� ���� ���
			session.setAttribute("mId", null);
			session.setAttribute("mPw", null);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�������� �ʴ� ȸ���Դϴ�!!'); history.go(-1); </script>");
			out.flush();
		} else if (!dto.getM_PW().equals(request.getParameter("M_PW"))) { // ��й�ȣ�� ��ġ���� ���� ���
			session.setAttribute("mId", null);
			session.setAttribute("mPw", null);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('��й�ȣ�� Ʋ�Ƚ��ϴ�!!'); history.go(-1); </script>");
			out.flush();
		} else { // ����
			session.setAttribute("mId", dto.getM_ID());
			session.setAttribute("mPw", dto.getM_PW());
			return "redirect:/main";
		}

		return "/Login";
	}

	// �α׾ƿ�
	@RequestMapping("/logout")
	public String MLogout(HttpServletResponse response, HttpServletRequest request, RedirectAttributes attr)
			throws Exception {
		System.out.println("Controller - Logout()");

		session.removeAttribute("user");
		session.invalidate();
		// mService.logout(response);

		return "redirect:/main";
	}

	// ȸ������
	@RequestMapping(value = "/doJoin", method = RequestMethod.POST)
	public String MJoin(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		System.out.println("Controller - joinForm()");
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		String emails[] = request.getParameter("mEmail").split("@");
		String births[] = request.getParameter("datepicker").split("-");

		map.put("mId", request.getParameter("mId"));
		map.put("mPw", request.getParameter("mPw"));
		map.put("mName", request.getParameter("mName"));
		map.put("mEmail1", emails[0]);
		map.put("mEmail2", emails[1]);
		map.put("mTel1", request.getParameter("mTel1"));
		map.put("mTel2", request.getParameter("mTel2"));
		map.put("mTel3", request.getParameter("mTel3"));
		map.put("mBirth1", births[0]);
		map.put("mBirth2", births[1]);
		map.put("mBirth3", births[2]);
		map.put("mFBCHK", request.getParameter("fcYN"));
		map.put("mKTCHK", request.getParameter("ktYN"));
		map.put("mNCHK", request.getParameter("nYN"));
		map.put("mGCHK", request.getParameter("gYN"));
		map.put("mEmailCHK", request.getParameter("emailYN"));
		map.put("mSmsCHK", request.getParameter("smsYN"));

		boolean chkId = mService.CheckId(request.getParameter("mId"));

		if (chkId) {
			boolean chkJoin = mService.MJoin(map);
			if (chkJoin) {
				return "redirect:/login";
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
				out.flush();
			}
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('���̵� �ߺ��Ǿ����ϴ�'); history.go(-1);</script>");
			out.flush();
		}
		return "/Login";
	}

	// IDã��
	@RequestMapping(value = "/findID", method = RequestMethod.POST)
	public String MFindID(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		String emails[] = request.getParameter("mIDEmail").split("@");

		map.put("mName", request.getParameter("mName"));
		map.put("mEmail1", emails[0]);
		map.put("mEmail2", emails[1]);

		String mID = mService.FindID(map);

		if ((mID == null) || (mID.equals(""))) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�������� �ʴ� ȸ���Դϴ�!!'); history.go(-1);</script>");
			out.flush();
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('���̵�� " + mID + " �Դϴ�');</script>");
			out.flush();
		}

		return "/Login";
	}

	// PWã��
	@RequestMapping(value = "/findPW", method = RequestMethod.POST)
	public String MFindPW(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		String emails[] = request.getParameter("mPWEmail").split("@");
		int pwdLength = 8;
		final char[] passwordTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
				'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^',
				'&', '*', '(', ')', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		Random random = new Random(System.currentTimeMillis());
		int tablelength = passwordTable.length;
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < pwdLength; i++) {
			buf.append(passwordTable[random.nextInt(tablelength)]);
		}

		map.put("mId", request.getParameter("mId"));
		map.put("mEmail1", emails[0]);
		map.put("mEmail2", emails[1]);

		boolean result = mService.FindPW(map);

		if (result) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�������� �ʴ� ȸ���Դϴ�!!'); history.go(-1);</script>");
			out.flush();
		} else {
			String newPW = buf.toString();
			map = new HashMap<String, Object>();
			map.put("mId", request.getParameter("mId"));
			map.put("mPW", newPW);

			mService.ChangePW(map);

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('��й�ȣ�� " + newPW + " �� �ʱ�ȭ�Ͽ����ϴ�');</script>");
			out.flush();
		}

		return "/Login";
	}

	// ��������
	@RequestMapping(value = "/doModify", method = RequestMethod.POST)
	public String MModify(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		String emails[] = request.getParameter("mEmail").split("@");
		if (!request.getParameter("mPw").equals(session.getAttribute("mPw").toString())) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�!!'); history.go(-1);</script>");
			out.flush();
		} else {

			map.put("mId", session.getAttribute("mId").toString());
			map.put("mName", request.getParameter("mName"));
			map.put("mEmail1", emails[0]);
			map.put("mEmail2", emails[1]);
			map.put("mTel1", request.getParameter("mTel1"));
			map.put("mTel2", request.getParameter("mTel2"));
			map.put("mTel3", request.getParameter("mTel3"));
			map.put("mFBCHK", request.getParameter("fcYN"));
			map.put("mKTCHK", request.getParameter("ktYN"));
			map.put("mNCHK", request.getParameter("nYN"));
			map.put("mGCHK", request.getParameter("gYN"));
			map.put("mEmailCHK", request.getParameter("emailReceiveYn"));
			map.put("mSmsCHK", request.getParameter("smsReceiveYn"));

			boolean chkModify = mService.ModifyInfo(map);
			if (chkModify) {
				return "/ModifyOK";
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('������ �߻��Ͽ����ϴ�!!'); history.go(-1);</script>");
				out.flush();
			}
		}

		return "/main";
	}

	// ��й�ȣ ����
	@RequestMapping(value = "/doChgPW", method = RequestMethod.POST)
	public String ChagePWD(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();

		if (!request.getParameter("nowPW").equals(session.getAttribute("mPw").toString())) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�!!'); history.go(-1);</script>");
			out.flush();
		} else {

			map.put("mId", session.getAttribute("mId").toString());
			map.put("mPW", request.getParameter("newPW"));

			boolean result = mService.ChagePWD(map);

			if (result) {
				session.removeAttribute("user");
				session.invalidate();
				return "redirect:/login";
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('������ �߻��Ͽ����ϴ�!!'); history.go(-1);</script>");
				out.flush();

				session.removeAttribute("user");
				session.invalidate();
				return "redirect:/login";
			}
		}
		
		return "/ChgPW";
	}
}
