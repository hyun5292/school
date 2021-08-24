package com.ses.controller;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ses.dto.LogDTO;
import com.ses.dto.MemberDTO;
import com.ses.dto.PageDTO;
import com.ses.dto.QnaDTO;
import com.ses.dto.SiteListDTO;
import com.ses.service.LService;
import com.ses.service.MService;
import com.ses.service.QService;
import com.ses.service.SLService;
import com.ses.service.SUService;

@Controller
@Repository
public class PageController {

	// ���� �������̽� ���� �ͼ� ���⼭ ����
	@Autowired
	LService Ser_L;
	@Autowired
	MService Ser_M;
	@Autowired
	QService Ser_Q;
	@Autowired
	SUService Ser_SU;
	@Autowired
	SLService Ser_SL;

	@Inject
	HttpSession session;

	// ����ȭ��
	@RequestMapping("/main")
	public String GoMain(HttpServletRequest request, Model model) {
		// ����Ʈ ����
		List<QnaDTO> dtos = Ser_Q.GetFour();

		if (session.getAttribute("mId") != null) {
			int cnt = Ser_SL.MemberPageCnt(session.getAttribute("mId").toString());

			model.addAttribute("Mcnt", cnt);
		}
		// �� �Ѱ��ֱ�
		model.addAttribute("dtos_qna", dtos);

		return "/MainScreen";
	}

	// �α���
	@RequestMapping("/login")
	public String GoLogin(HttpServletRequest request, Model model) {
		return "/Login";
	}

	// ȸ����������
	@RequestMapping("/modify")
	public String GoModify(HttpServletRequest request, Model model) {
		MemberDTO dto = Ser_M.GetInfo(session.getAttribute("mId").toString());

		model.addAttribute("mName", dto.getM_NAME());
		model.addAttribute("birth", dto.getM_BIRTH1() + "-" + dto.getM_BIRTH2() + "-" + dto.getM_BIRTH3());
		model.addAttribute("tel1", "0" + dto.getM_TEL1());
		model.addAttribute("tel2", dto.getM_TEL2());
		model.addAttribute("tel3", dto.getM_TEL3());
		model.addAttribute("mEmail", dto.getM_EMAIL1() + "@" + dto.getM_EMAIL2());
		model.addAttribute("fcYN", dto.getM_FBCHK());
		model.addAttribute("ktYN", dto.getM_KTCHK());
		model.addAttribute("nYN", dto.getM_NCHK());
		model.addAttribute("gYN", dto.getM_GCHK());
		model.addAttribute("emailYN", dto.getM_EMAIL_CHK());
		model.addAttribute("smsYN", dto.getM_SMS_CHK());

		return "/ModifyInfo";
	}

	// ȸ������
	@RequestMapping("/join")
	public String GoJoin(HttpServletRequest request, Model model) {
		// �ۼ� ��¥ �������� timestamp �ۼ��� ��
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String max = (ts.getYear() + 1900) - 18 + "-12-31";
		String min = (ts.getYear() + 1900) - 101 + "-01-01";

		model.addAttribute("max", max);
		model.addAttribute("min", min);

		return "/Join";
	}

	// �����ϱ� ��й�ȣ Ȯ��
	@RequestMapping("/QnaPwdForm")
	public String Check_Qna_PWD(HttpServletRequest request, Model model) {
		// �� �Ѱ��ֱ�
		model.addAttribute("Qnum", request.getParameter("Qnum"));

		return "/QnaPwd";
	}

	// ����/ȸ�� �Ұ�
	@RequestMapping("/serviceInfo")
	public String GoServiceInfo(HttpServletRequest request, Model model) {
		return "/ServiceInfo";
	}

	// ��й�ȣ ����
	@RequestMapping("/chgPW")
	public String GoChgPW(HttpServletRequest request, Model model) {
		return "/ChgPW";
	}

	// ���� �̿� ����
	@RequestMapping("/serviceStep")
	public String GoServiceStep(HttpServletRequest request, Model model) {
		return "/ServiceStep";
	}

	// �����ϱ�
	@RequestMapping("/qna")
	public String GoQna(HttpServletRequest request, Model model) {
		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null�̸� �� ó��
			pgNum = "1";
		// int������
		int pgnum = Integer.parseInt(pgNum);
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();

		// ��ü �Խñ� ���� ����
		pgDTO.setTotalCnt(Ser_Q.PageCnt());
		// ���� ������ ��ȣ ����
		pgDTO.setPageNum(pgnum);
		// ������ �Խù� �� ����
		pgDTO.setContentNum(5);
		// ���� ������ ��� ����
		pgDTO.setCurBlock(pgnum);
		// ������ ��� ��ȣ ����
		pgDTO.setLastBlock(pgDTO.getTotalCnt());
		// ���� ȭ��ǥ ǥ�� ����
		pgDTO.prevnext(pgnum);
		// ���� ������ ����
		pgDTO.setStartPage(pgDTO.getCurBlock());
		// ������ ������ ����
		pgDTO.setEndPage(pgDTO.getLastBlock(), pgDTO.getCurBlock());

		map.put("startNum", (pgnum - 1) * pgDTO.getContentNum());
		map.put("ContentNum", pgDTO.getContentNum());

		List<QnaDTO> dtos = Ser_Q.GetQList(map);

		int first = (pgnum - 1) * pgDTO.getContentNum() + 1;
		int last = first + pgDTO.getContentNum();
		int j = 0;
		// �� �Խù� ��ȣ
		for (int i = first; i < last; i++) {
			if (i <= pgDTO.getTotalCnt()) {
				dtos.get(j).setNUM(i);
				j++;
			}
		}

		String prev = "", next = ""; // <, >

		if (pgDTO.isPrev()) { // ���� ����� �����ϴ°�
			prev = "<";
		}
		if (pgDTO.isNext()) { // ���� ����� �����ϴ°�
			next = ">";
		}

		// �Ѿ�� ��µ� ������ ��ȣ��
		int[] pg;
		if (dtos.size() == 0) {
			pg = new int[1];
		} else {
			pg = new int[(pgDTO.getEndPage() - pgDTO.getStartPage()) + 1];
		}

		// ������ �ڹٽ�ũ��Ʈ �Ἥ ����ߵǴµ� ���� ���� �� �����ͼ� ��ġ�ؾ� �ȴٱ淡
		// �׳� ���⼭ �� ����ؼ� �Ѱ��ֱ�
		j = 0;
		for (int i = pgDTO.getStartPage(); i < pgDTO.getStartPage() + pgDTO.getContentNum(); i++) {
			if (pg.length > j)
				pg[j] = i;
			j++;
		}

		// �� �Ѱ��ֱ�
		model.addAttribute("dtos", dtos);
		model.addAttribute("before", pgDTO.getStartPage() - 1);
		model.addAttribute("after", pgDTO.getEndPage() + 1);
		model.addAttribute("prev", prev);
		model.addAttribute("pg", pg);
		model.addAttribute("next", next);
		if (pgDTO.getTotalCnt() % pgDTO.getContentNum() > 0)
			model.addAttribute("last", pgDTO.getTotalCnt() / pgDTO.getContentNum() + 1);
		else
			model.addAttribute("last", pgDTO.getTotalCnt() / pgDTO.getContentNum());

		return "/Qna";
	}

	// �����ϱ� �ۼ� ��
	@RequestMapping("/qnaWrite")
	public String GoQnaWrite(HttpServletRequest request, Model model) {
		int num = Ser_Q.PageCnt() + 1;

		// �� �Ѱ��ֱ�
		model.addAttribute("QNum", num);
		model.addAttribute("M_ID", session.getAttribute("mId"));

		return "/QnaWrite";
	}

	// ������ ��ȸ
	@RequestMapping("/easySearch")
	public String GoEasySearch(HttpServletRequest request, Model model) {
		if (session.getAttribute("mId") == null)
			return "redirect:/main";

		String kind = request.getParameter("kind");
		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null�̸� �� ó��
			pgNum = "1";
		// int������
		int pgnum = Integer.parseInt(pgNum);
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();

		map.put("kind", kind);
		map.put("M_ID", session.getAttribute("mId"));

		// ��ü �Խñ� ���� ����
		pgDTO.setTotalCnt(Ser_SL.PageCnt(map));
		// ���� ������ ��ȣ ����
		pgDTO.setPageNum(pgnum);
		// ������ �Խù� �� ����
		pgDTO.setContentNum(5);
		// ���� ������ ��� ����
		pgDTO.setCurBlock(pgnum);
		// ������ ��� ��ȣ ����
		pgDTO.setLastBlock(pgDTO.getTotalCnt());
		// ���� ȭ��ǥ ǥ�� ����
		pgDTO.prevnext(pgnum);
		// ���� ������ ����
		pgDTO.setStartPage(pgDTO.getCurBlock());
		// ������ ������ ����
		pgDTO.setEndPage(pgDTO.getLastBlock(), pgDTO.getCurBlock());

		map = new HashMap<String, Object>();
		map.put("kind", kind);
		map.put("M_ID", session.getAttribute("mId"));
		map.put("startNum", (pgnum - 1) * pgDTO.getContentNum());
		map.put("ContentNum", pgDTO.getContentNum());

		List<SiteListDTO> dtos = Ser_SL.GetSLList(map);

		int first = (pgnum - 1) * pgDTO.getContentNum() + 1;
		int last = first + pgDTO.getContentNum();
		int j = 0;
		// �� �Խù� ��ȣ
		for (int i = first; i < last; i++) {
			if (i <= pgDTO.getTotalCnt()) {
				dtos.get(j).setNUM(i);
				j++;
			}
		}

		String prev = "", next = ""; // <, >

		if (pgDTO.isPrev()) { // ���� ����� �����ϴ°�
			prev = "<";
		}
		if (pgDTO.isNext()) { // ���� ����� �����ϴ°�
			next = ">";
		}

		// �Ѿ�� ��µ� ������ ��ȣ��
		int[] pg;
		if (dtos.size() == 0) {
			pg = new int[1];
		} else {
			pg = new int[(pgDTO.getEndPage() - pgDTO.getStartPage()) + 1];
		}

		// ������ �ڹٽ�ũ��Ʈ �Ἥ ����ߵǴµ� ���� ���� �� �����ͼ� ��ġ�ؾ� �ȴٱ淡
		// �׳� ���⼭ �� ����ؼ� �Ѱ��ֱ�
		j = 0;
		for (int i = pgDTO.getStartPage(); i < pgDTO.getStartPage() + pgDTO.getContentNum(); i++) {
			if (pg.length > j)
				pg[j] = i;
			j++;
		}

		// �� �Ѱ��ֱ�
		model.addAttribute("dtos", dtos);
		model.addAttribute("before", pgDTO.getStartPage() - 1);
		model.addAttribute("after", pgDTO.getEndPage() + 1);
		model.addAttribute("prev", prev);
		model.addAttribute("pg", pg);
		model.addAttribute("next", next);
		if (pgDTO.getTotalCnt() % pgDTO.getContentNum() > 0)
			model.addAttribute("last", pgDTO.getTotalCnt() / pgDTO.getContentNum() + 1);
		else
			model.addAttribute("last", pgDTO.getTotalCnt() / pgDTO.getContentNum());
		model.addAttribute("kind", kind);

		return "/EasySearch";
	}

	// ������ Ż�� ����
	@RequestMapping("/searchLog")
	public String GoSearchLog(HttpServletRequest request, Model model) {
		if (session.getAttribute("mId") == null)
			return "redirect:/main";

		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null�̸� �� ó��
			pgNum = "1";
		// int������
		int pgnum = Integer.parseInt(pgNum);
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();

		map.put("M_ID", session.getAttribute("mId"));

		// ��ü �Խñ� ���� ����
		pgDTO.setTotalCnt(Ser_L.PageCnt(map));
		// ���� ������ ��ȣ ����
		pgDTO.setPageNum(pgnum);
		// ������ �Խù� �� ����
		pgDTO.setContentNum(5);
		// ���� ������ ��� ����
		pgDTO.setCurBlock(pgnum);
		// ������ ��� ��ȣ ����
		pgDTO.setLastBlock(pgDTO.getTotalCnt());
		// ���� ȭ��ǥ ǥ�� ����
		pgDTO.prevnext(pgnum);
		// ���� ������ ����
		pgDTO.setStartPage(pgDTO.getCurBlock());
		// ������ ������ ����
		pgDTO.setEndPage(pgDTO.getLastBlock(), pgDTO.getCurBlock());

		map.put("M_ID", session.getAttribute("mId"));
		map.put("startNum", (pgnum - 1) * pgDTO.getContentNum());
		map.put("ContentNum", pgDTO.getContentNum());

		// ����Ʈ ����
		List<LogDTO> dtos = Ser_L.GetLList(map);

		int first = (pgnum - 1) * pgDTO.getContentNum() + 1;
		int last = first + pgDTO.getContentNum();
		String str = "";
		int j = 0;
		// �� �Խù� ��ȣ
		for (int i = first; i < last; i++) {
			if (i <= pgDTO.getTotalCnt()) {
				dtos.get(j).setNUM(i);
				str = dtos.get(j).getL_YEAR() + "-" + dtos.get(j).getL_MONTH() + "-" + dtos.get(j).getL_DAY() + " "
						+ dtos.get(j).getL_HOUR() + ":" + dtos.get(j).getL_MINUTE();
				dtos.get(j).setDATE_HOUR(str);

				j++;
			}
		}

		String prev = "", next = ""; // <, >

		if (pgDTO.isPrev()) { // ���� ����� �����ϴ°�
			prev = "<";
		}
		if (pgDTO.isNext()) { // ���� ����� �����ϴ°�
			next = ">";
		}

		int[] pg;
		// �Ѿ�� ��µ� ������ ��ȣ��
		if (dtos.size() == 0) {
			pg = new int[1];
		} else {
			pg = new int[(pgDTO.getEndPage() - pgDTO.getStartPage()) + 1];
		}

		// ������ �ڹٽ�ũ��Ʈ �Ἥ ����ߵǴµ� ���� ���� �� �����ͼ� ��ġ�ؾ� �ȴٱ淡
		// �׳� ���⼭ �� ����ؼ� �Ѱ��ֱ�
		j = 0;
		for (int i = pgDTO.getStartPage(); i < pgDTO.getStartPage() + pgDTO.getContentNum(); i++) {
			if (pg.length > j)
				pg[j] = i;
			j++;
		}

		// �� �Ѱ��ֱ�
		model.addAttribute("dtos", dtos);
		model.addAttribute("before", pgDTO.getStartPage() - 1);
		model.addAttribute("after", pgDTO.getEndPage() + 1);
		model.addAttribute("prev", prev);
		model.addAttribute("pg", pg);
		model.addAttribute("next", next);
		if (pgDTO.getTotalCnt() % pgDTO.getContentNum() > 0)
			model.addAttribute("last", pgDTO.getTotalCnt() / pgDTO.getContentNum() + 1);
		else
			model.addAttribute("last", pgDTO.getTotalCnt() / pgDTO.getContentNum());

		return "/SearchLog";
	}

	// ȸ�� ID, PW ã��
	@RequestMapping("/viewfindIDPW")
	public String FindIDPW(HttpServletRequest request, Model model) {
		return "/FindIDPW";
	}
}
