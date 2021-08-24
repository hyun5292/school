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

	// 키워드 검색
	@RequestMapping("/search")
	public String SearchedList(HttpServletRequest request, Model model) {
		if (session.getAttribute("mId") == null)
			return "redirect:index";
		
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();
		// 키워드 가져오기
		String keyword = request.getParameter("keyword");
		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null이면 맨 처음
			pgNum = "1";
		// int형으로
		int pgnum = Integer.parseInt(pgNum);
		String kind = request.getParameter("kind");
		PageDTO pgDTO = new PageDTO();

		map.put("kind", kind);
		map.put("M_ID", session.getAttribute("mId"));
		map.put("keyword", "%" + request.getParameter("keyword") + "%");

		// 전체 게시글 개수 설정
		pgDTO.setTotalCnt(Ser_SL.SearchedListCnt(map));
		// 현재 페이지 번호 설정
		pgDTO.setPageNum(pgnum);
		// 보여줄 게시물 수 설정
		pgDTO.setContentNum(5);
		// 현재 페이지 블록 설정
		pgDTO.setCurBlock(pgnum);
		// 마지막 블록 번호 설정
		pgDTO.setLastBlock(pgDTO.getTotalCnt());
		// 이전 화살표 표시 여부
		pgDTO.prevnext(pgnum);
		// 시작 페이지 설정
		pgDTO.setStartPage(pgDTO.getCurBlock());
		// 마지막 페이지 설정
		pgDTO.setEndPage(pgDTO.getLastBlock(), pgDTO.getCurBlock());

		map.put("kind", kind);
		map.put("M_ID", session.getAttribute("mId"));
		map.put("keyword", "%" + keyword + "%");
		map.put("startNum", (pgnum - 1) * pgDTO.getContentNum());
		map.put("ContentNum", pgDTO.getContentNum());
		
		// 리스트 내용
		List<SiteListDTO> dtos = Ser_SL.SearchList(map);

		int first = (pgnum - 1) * pgDTO.getContentNum() + 1;
		int last = first + pgDTO.getContentNum();
		int j = 0;
		
		// 각 게시물 번호
		for (int i = first; i < last; i++) {
			if (i <= pgDTO.getTotalCnt()) {
				dtos.get(j).setNUM(i);
				j++;
			}
		}

		String prev = "", next = ""; // <, >

		if (pgDTO.isPrev()) { // 이전 블록이 존재하는가
			prev = "<";
		}
		if (pgDTO.isNext()) { // 다음 블록이 존재하는가
			next = ">";
		}

		// 넘어가서 출력될 페이지 번호들
		int[] pg;
		if (dtos.size() == 0) {
			pg = new int[1];
		} else {
			pg = new int[(pgDTO.getEndPage() - pgDTO.getStartPage()) + 1];
		}

		// 원래는 자바스크립트 써서 해줘야되는데 무슨 파일 또 가져와서 설치해야 된다길래
		// 그냥 여기서 값 계산해서 넘겨주기
		j = 0;
		for (int i = pgDTO.getStartPage(); i < pgDTO.getStartPage() + pgDTO.getContentNum(); i++) {
			if (pg.length > j)
				pg[j] = i;
			j++;
		}

		// 값 넘겨주기
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
	
	// 사이트 연결 해지
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
		map_l.put("L_ACTIVITY", "해지");
		map_l.put("L_YEAR", y);
		map_l.put("L_MONTH", mm);
		map_l.put("L_DAY", d);
		map_l.put("L_HOUR", h);
		map_l.put("L_MINUTE", m);
		
		boolean result_log = Ser_L.InputLog(map_l);
		if(result_cancel && result_log) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('해지되었습니다!!'); document.location.replace('easySearch?kind=facebook')</script>");
			out.flush();
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('오류가 발생하였습니다!!'); history.go(-1);</script>");
			out.flush();
		}
		
		return "/easySearch?kind=facebook";
	}
}
