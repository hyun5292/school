package com.spring.hamsamo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.hamsamo.dto.BoardDTO;
import com.spring.hamsamo.dto.PageDTO;
import com.spring.hamsamo.service.BoardService;

@Controller
@Repository(value = "/page")
public class PageController {
	@Autowired
	BoardService bService;

	@Inject
	HttpSession session;

	// 메인화면
	@RequestMapping("/index")
	public String MIndex(HttpServletRequest request, Model model) {
		System.out.println("Index");
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();
		// 페이징 정보가 담긴 DTO
		PageDTO pDto = new PageDTO();

		// 현재 페이지 번호를 가져오기
		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null이면 맨 처음
			pgNum = "1";
		String contNum = "5"; // 한 페이지 당 5개씩
		// int형으로
		int cPgNum = Integer.parseInt(pgNum);
		int cContNum = Integer.parseInt(contNum);

		pDto.setTotalCnt(bService.PageCnt()); // 전체 글 개수
		pDto.setPageNum(cPgNum); // 현재 페이지
		pDto.setContentNum(cContNum); // 한 페이지에 몇 개씩
		pDto.setCurBlock(cPgNum); // 현재 페이지 블록
		pDto.setLastBlock(pDto.getTotalCnt()); // 마지막 블록 번호

		pDto.prevnext(cPgNum); // 이전 화살표 표시 여부
		pDto.setStartPage(pDto.getCurBlock()); // 시작 페이지
		pDto.setEndPage(pDto.getLastBlock(), pDto.getCurBlock()); // 마지막 페이지

		// 넘어가서 출력될 페이지 번호들
		int[] pg = new int[(pDto.getEndPage() - pDto.getStartPage()) + 1];

		// sql문에 넘겨줄꺼
		map.put("PageNum", ((cPgNum * cContNum) - (cContNum - 1)) - 1);
		map.put("ContentNum", cContNum); // 몇 개

		// 해당 리스트 불러오기
		List<BoardDTO> dtos = bService.GetBList(map);
		String prev = "", next = ""; // <, >

		if (pDto.isPrev()) { // 이전 블록이 존재하는가
			prev = "<";
		}
		if (pDto.isNext()) { // 다음 블록이 존재하는가
			next = ">";
		}

		// 원래는 자바스크립트 써서 해줘야되는데 무슨 파일 또 가져와서 설치해야 된다길래
		// 그냥 여기서 값 계산해서 넘겨주기
		int j = 0;
		for (int i = pDto.getStartPage(); i < pDto.getStartPage() + cContNum; i++) {
			if (pg.length > j)
				pg[j] = i;
			if (dtos.size() > j)
				dtos.get(j).setbNum(dtos.get(j).getbGroup());
			j++;
		}

		// 값 넘겨주기
		model.addAttribute("dtos", dtos);
		model.addAttribute("before", pDto.getStartPage() - 1);
		model.addAttribute("after", pDto.getEndPage() + 1);
		model.addAttribute("prev", prev);
		model.addAttribute("pg", pg);
		model.addAttribute("next", next);
		if (pDto.getTotalCnt() % cContNum > 0)
			model.addAttribute("last", pDto.getTotalCnt() / cContNum + 1);
		else
			model.addAttribute("last", pDto.getTotalCnt() / cContNum);

		return "/index";
	}

	// 메인화면
	@RequestMapping("/list")
	public String MList(HttpServletRequest request, Model model) {
		System.out.println("List");
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();
		// 페이징 정보가 담긴 DTO
		PageDTO pDto = new PageDTO();

		if (session.getAttribute("mId") == null)
			return "redirect:index";

		// 현재 페이지 번호를 가져오기
		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null이면 맨 처음
			pgNum = "1";
		String contNum = "5"; // 한 페이지 당 5개씩
		// int형으로
		int cPgNum = Integer.parseInt(pgNum);
		int cContNum = Integer.parseInt(contNum);

		pDto.setTotalCnt(bService.PageCnt()); // 전체 글 개수
		pDto.setPageNum(cPgNum); // 현재 페이지
		pDto.setContentNum(cContNum); // 한 페이지에 몇 개씩
		pDto.setCurBlock(cPgNum); // 현재 페이지 블록
		pDto.setLastBlock(pDto.getTotalCnt()); // 마지막 블록 번호

		pDto.prevnext(cPgNum); // 이전 화살표 표시 여부
		pDto.setStartPage(pDto.getCurBlock()); // 시작 페이지
		pDto.setEndPage(pDto.getLastBlock(), pDto.getCurBlock()); // 마지막 페이지

		// 넘어가서 출력될 페이지 번호들
		int[] pg = new int[(pDto.getEndPage() - pDto.getStartPage()) + 1];

		// sql문에 넘겨줄꺼
		map.put("PageNum", ((cPgNum * cContNum) - (cContNum - 1)) - 1);
		map.put("ContentNum", cContNum); // 몇 개

		// 해당 리스트 불러오기
		List<BoardDTO> dtos = bService.GetBList(map);
		String prev = "", next = ""; // <, >

		if (pDto.isPrev()) { // 이전 블록이 존재하는가
			prev = "<";
		}
		if (pDto.isNext()) { // 다음 블록이 존재하는가
			next = ">";
		}

		// 원래는 자바스크립트 써서 해줘야되는데 무슨 파일 또 가져와서 설치해야 된다길래
		// 그냥 여기서 값 계산해서 넘겨주기
		int j = 0;
		for (int i = pDto.getStartPage(); i < pDto.getStartPage() + cContNum; i++) {
			if (pg.length > j)
				pg[j] = i;
			if (dtos.size() > j)
				dtos.get(j).setbNum(dtos.get(j).getbGroup());
			j++;
		}

		// 값 넘겨주기
		model.addAttribute("dtos", dtos);
		model.addAttribute("before", pDto.getStartPage() - 1);
		model.addAttribute("after", pDto.getEndPage() + 1);
		model.addAttribute("prev", prev);
		model.addAttribute("pg", pg);
		model.addAttribute("next", next);
		if (pDto.getTotalCnt() % cContNum > 0)
			model.addAttribute("last", pDto.getTotalCnt() / cContNum + 1);
		else
			model.addAttribute("last", pDto.getTotalCnt() / cContNum);

		return "/list";
	}

	// 메인화면
	@RequestMapping("/search")
	public String MSearchedList(HttpServletRequest request, Model model) {
		System.out.println("SearchedList");
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();
		// 페이징 정보가 담긴 DTO
		PageDTO pDto = new PageDTO();

		if (session.getAttribute("mId") == null)
			return "redirect:index";

		String col = request.getParameter("bCol");
		if (col.equals("작성자")) {
			map.put("bVal1", "%" + request.getParameter("bVal") + "%");
			map.put("bVal2", "%");
			map.put("bVal3", "%");
		} else if (col.equals("제목")) {
			map.put("bVal1", "%");
			map.put("bVal2", "%" + request.getParameter("bVal") + "%");
			map.put("bVal3", "%");
		} else {
			map.put("bVal1", "%");
			map.put("bVal2", "%");
			map.put("bVal3", "%" + request.getParameter("bVal") + "%");
		}

		// 해당 리스트 전체 불러오기
		List<BoardDTO> dtos_origin = bService.GetBSearchedList(map);
		for (int i = 0; i < dtos_origin.size(); i++) {
			dtos_origin.get(i).setbNum(dtos_origin.size() - i);
		}

		// 현재 페이지 번호를 가져오기
		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null이면 맨 처음
			pgNum = "1";
		String contNum = "5"; // 한 페이지 당 5개씩
		// int형으로
		int cPgNum = Integer.parseInt(pgNum);
		int cContNum = Integer.parseInt(contNum);

		pDto.setTotalCnt(dtos_origin.size()); // 전체 글 개수
		pDto.setPageNum(cPgNum); // 현재 페이지
		pDto.setContentNum(cContNum); // 한 페이지에 몇 개씩
		pDto.setCurBlock(cPgNum); // 현재 페이지 블록
		pDto.setLastBlock(pDto.getTotalCnt()); // 마지막 블록 번호

		pDto.prevnext(cPgNum); // 이전 화살표 표시 여부
		pDto.setStartPage(pDto.getCurBlock()); // 시작 페이지
		pDto.setEndPage(pDto.getLastBlock(), pDto.getCurBlock()); // 마지막 페이지

		// 넘어가서 출력될 페이지 번호들
		int[] pg = new int[(pDto.getEndPage() - pDto.getStartPage()) + 1];

		String prev = "", next = ""; // <, >

		if (pDto.isPrev()) { // 이전 블록이 존재하는가
			prev = "<";
		}
		if (pDto.isNext()) { // 다음 블록이 존재하는가
			next = ">";
		}

		// 원래는 자바스크립트 써서 해줘야되는데 무슨 파일 또 가져와서 설치해야 된다길래
		// 그냥 여기서 값 계산해서 넘겨주기
		int j = 0;
		List<BoardDTO> dtos = new ArrayList<BoardDTO>();
		for (int i = pDto.getStartPage(); i < pDto.getStartPage() + cContNum; i++) {
			if (pg.length > j)
				pg[j] = i;
			if (cPgNum == pDto.getEndPage()) {
				if (dtos.size() < pDto.getTotalCnt() % cContNum) {
					dtos.add(dtos_origin.get((cPgNum * cContNum) - (cContNum - j)));
				}
			} else {
				dtos.add(dtos_origin.get((cPgNum * cContNum) - (cContNum - j)));
			}
			j++;
		}

		// 값 넘겨주기
		model.addAttribute("bCol", request.getParameter("bCol").toString());
		model.addAttribute("bVal", request.getParameter("bVal").toString());
		model.addAttribute("dtos", dtos);
		model.addAttribute("before", pDto.getStartPage() - 1);
		model.addAttribute("after", pDto.getEndPage() + 1);
		model.addAttribute("prev", prev);
		model.addAttribute("pg", pg);
		model.addAttribute("next", next);
		if (pDto.getTotalCnt() % cContNum > 0)
			model.addAttribute("last", pDto.getTotalCnt() / cContNum + 1);
		else
			model.addAttribute("last", pDto.getTotalCnt() / cContNum);
		return "/searchedList";
	}
}
