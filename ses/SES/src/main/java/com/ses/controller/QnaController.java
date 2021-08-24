package com.ses.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
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

import com.ses.dto.LogDTO;
import com.ses.dto.PageDTO;
import com.ses.dto.QnaDTO;
import com.ses.dto.SiteListDTO;
import com.ses.service.QService;

@Controller
@Repository
public class QnaController {

	@Autowired
	QService Ser_Q;
	
	@Inject
	HttpSession session;

	// 메인화면
	@RequestMapping("/showQna")
	public String GoMain(HttpServletRequest request, Model model) {
		String Q_NUM = request.getParameter("Qnum");
		int Qnum = Integer.parseInt(Q_NUM);

		QnaDTO dto = Ser_Q.GetQna(Qnum);

		// 값 넘겨주기
		model.addAttribute("dto", dto);

		return "/QnaForm";
	}

	// 키워드 검색
	@RequestMapping("/searchqna")
	public String SearchedList(HttpServletRequest request, Model model) {
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();
		String bCol = request.getParameter("bCol");
		String bVal = request.getParameter("bVal");
		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null이면 맨 처음
			pgNum = "1";
		// int형으로
		int pgnum = Integer.parseInt(pgNum);

		if (bCol.equals("제목")) {
			map.put("bVal1", "%" + bVal + "%");
			map.put("bVal2", "%");
		} else if (bCol.equals("작성자")) {
			map.put("bVal1", "%");
			map.put("bVal2", "%" + bVal + "%");
		}

		// 전체 게시글 개수 설정
		pgDTO.setTotalCnt(Ser_Q.SearchedListCnt(map));
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

		if (bCol.equals("제목")) {
			map.put("bVal1", "%" + bVal + "%");
			map.put("bVal2", "%");
		} else if (bCol.equals("작성자")) {
			map.put("bVal1", "%");
			map.put("bVal2", "%" + bVal + "%");
		}
		map.put("startNum", (pgnum - 1) * pgDTO.getContentNum());
		map.put("ContentNum", pgDTO.getContentNum());

		// 리스트 내용
		List<QnaDTO> dtos = Ser_Q.SearchList(map);

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
		model.addAttribute("bCol", bCol);
		model.addAttribute("bVal", bVal);
		model.addAttribute("before", pgDTO.getStartPage() - 1);
		model.addAttribute("after", pgDTO.getEndPage() + 1);
		model.addAttribute("prev", prev);
		model.addAttribute("pg", pg);
		model.addAttribute("next", next);
		if (pgDTO.getTotalCnt() % pgDTO.getContentNum() > 0)
			model.addAttribute("last", pgDTO.getTotalCnt() / pgDTO.getContentNum() + 1);
		else
			model.addAttribute("last", pgDTO.getTotalCnt() / pgDTO.getContentNum());

		return "/QnaSearched";
	}

	// Qna 작성
	@RequestMapping("/writeQna")
	public String newQna(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();

		int y = 0, m = 0, d = 0;
		Date time = new Date();
		y = time.getYear() + 1900;
		m = time.getMonth() + 1;
		d = time.getDate();

		map.put("Q_TITLE", request.getParameter("qTitle"));
		map.put("M_ID", session.getAttribute("mId"));
		map.put("Q_PWD", Integer.parseInt(request.getParameter("qPwd")));
		map.put("Q_CONTENT", request.getParameter("qQna"));
		map.put("Q_YEAR", y);
		map.put("Q_MONTH", m);
		map.put("Q_DAY", d);
		map.put("Q_REPLY", null);

		boolean result = Ser_Q.NewQna(map);

		if (result) {
			return "redirect:/qna";
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('오류가 발생하였습니다!!'); history.go(-1);</script>");
			out.flush();
		}

		return "/qna";
	}

	// 비밀번호 확인 후 해당 페이지로
	@RequestMapping(value = "/ChkQnaPwd", method = RequestMethod.POST)
	public String ChkPWD(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		String next = "";
		boolean result = false;

		QnaDTO dto = Ser_Q.GetQna(Integer.parseInt(request.getParameter("Qnum")));

		if (dto.getQ_PWD() == Integer.parseInt(request.getParameter("Q_PWD"))) {
			next = "redirect:/showQna?Qnum=" + dto.getQ_NUM();
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호가 일치하지 않습니다!!'); history.go(-1);</script>");
			out.flush();
		}

		return next;
	}
}
