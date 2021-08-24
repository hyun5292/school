package com.ses.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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

import com.ses.dto.PageDTO;
import com.ses.dto.SiteListDTO;
import com.ses.service.LService;
import com.ses.service.SLService;

@Controller
@Repository
public class SiteListController {

	@Autowired
	SLService Ser_SL;
	@Autowired
	LService Ser_L;
	
	@Inject
	HttpSession session;

	// Ű���� �˻�
	@RequestMapping("/search")
	public String SearchedList(HttpServletRequest request, Model model) {
		if (session.getAttribute("mId") == null)
			return "redirect:index";
		
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		// Ű���� ��������
		String keyword = request.getParameter("keyword");
		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null�̸� �� ó��
			pgNum = "1";
		// int������
		int pgnum = Integer.parseInt(pgNum);
		String kind = request.getParameter("kind");
		PageDTO pgDTO = new PageDTO();

		map.put("kind", kind);
		map.put("M_ID", session.getAttribute("mId"));
		map.put("keyword", "%" + request.getParameter("keyword") + "%");

		// ��ü �Խñ� ���� ����
		pgDTO.setTotalCnt(Ser_SL.SearchedListCnt(map));
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

		map.put("kind", kind);
		map.put("M_ID", session.getAttribute("mId"));
		map.put("keyword", "%" + keyword + "%");
		map.put("startNum", (pgnum - 1) * pgDTO.getContentNum());
		map.put("ContentNum", pgDTO.getContentNum());
		
		// ����Ʈ ����
		List<SiteListDTO> dtos = Ser_SL.SearchList(map);

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
		model.addAttribute("keyword", keyword);
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

		return "/EasySearched";
	}
	
	// ����Ʈ ���� ����
	@RequestMapping("/cancel")
	public String CancelSNS(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		if (session.getAttribute("mId") == null)
			return "redirect:index";
		
		Map<String, Object> map_sl = new HashMap<String, Object>();
		Map<String, Object> map_l = new HashMap<String, Object>();
		String title = request.getParameter("sl_Name");
		int y = 0, mm = 0, d = 0, h = 0, m = 0;
		Date time = new Date();
		y = time.getYear()+1900;
		mm = time.getMonth()+1;
		d = time.getDate();
		h = time.getHours();
		m = time.getMinutes();
		
		map_sl.put("SL_NAME", title);
		map_sl.put("M_ID", session.getAttribute("mId"));
		
		boolean result_cancel = Ser_SL.CancelSNS(map_sl);
		
		SiteListDTO dto = Ser_SL.SearchOne(map_sl);
		
		map_l.put("M_ID", session.getAttribute("mId"));
		map_l.put("SL_NAME", title);
		map_l.put("SL_SITE", dto.getSL_SITE());
		map_l.put("SU_KIND", dto.getSU_KIND());
		map_l.put("L_ACTIVITY", "����");
		map_l.put("L_YEAR", y);
		map_l.put("L_MONTH", mm);
		map_l.put("L_DAY", d);
		map_l.put("L_HOUR", h);
		map_l.put("L_MINUTE", m);
		
		boolean result_log = Ser_L.InputLog(map_l);
		if(result_cancel && result_log) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�����Ǿ����ϴ�!!'); document.location.replace('easySearch?kind=facebook')</script>");
			out.flush();
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��Ͽ����ϴ�!!'); history.go(-1);</script>");
			out.flush();
		}
		
		return "/easySearch?kind=facebook";
	}
}
