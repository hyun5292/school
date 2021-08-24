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

	// 서비스 인터페이스 갖고 와서 여기서 정의
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

	// 메인화면
	@RequestMapping("/main")
	public String GoMain(HttpServletRequest request, Model model) {
		// 리스트 내용
		List<QnaDTO> dtos = Ser_Q.GetFour();

		if (session.getAttribute("mId") != null) {
			int cnt = Ser_SL.MemberPageCnt(session.getAttribute("mId").toString());

			model.addAttribute("Mcnt", cnt);
		}
		// 값 넘겨주기
		model.addAttribute("dtos_qna", dtos);

		return "/MainScreen";
	}

	// 로그인
	@RequestMapping("/login")
	public String GoLogin(HttpServletRequest request, Model model) {
		return "/Login";
	}

	// 회원정보수정
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

	// 회원가입
	@RequestMapping("/join")
	public String GoJoin(HttpServletRequest request, Model model) {
		// 작성 날짜 넣으려고 timestamp 작성한 거
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String max = (ts.getYear() + 1900) - 18 + "-12-31";
		String min = (ts.getYear() + 1900) - 101 + "-01-01";

		model.addAttribute("max", max);
		model.addAttribute("min", min);

		return "/Join";
	}

	// 문의하기 비밀번호 확인
	@RequestMapping("/QnaPwdForm")
	public String Check_Qna_PWD(HttpServletRequest request, Model model) {
		// 값 넘겨주기
		model.addAttribute("Qnum", request.getParameter("Qnum"));

		return "/QnaPwd";
	}

	// 서비스/회사 소개
	@RequestMapping("/serviceInfo")
	public String GoServiceInfo(HttpServletRequest request, Model model) {
		return "/ServiceInfo";
	}

	// 비밀번호 변경
	@RequestMapping("/chgPW")
	public String GoChgPW(HttpServletRequest request, Model model) {
		return "/ChgPW";
	}

	// 서비스 이용 절차
	@RequestMapping("/serviceStep")
	public String GoServiceStep(HttpServletRequest request, Model model) {
		return "/ServiceStep";
	}

	// 문의하기
	@RequestMapping("/qna")
	public String GoQna(HttpServletRequest request, Model model) {
		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null이면 맨 처음
			pgNum = "1";
		// int형으로
		int pgnum = Integer.parseInt(pgNum);
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();

		// 전체 게시글 개수 설정
		pgDTO.setTotalCnt(Ser_Q.PageCnt());
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

		map.put("startNum", (pgnum - 1) * pgDTO.getContentNum());
		map.put("ContentNum", pgDTO.getContentNum());

		List<QnaDTO> dtos = Ser_Q.GetQList(map);

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

	// 문의하기 작성 폼
	@RequestMapping("/qnaWrite")
	public String GoQnaWrite(HttpServletRequest request, Model model) {
		int num = Ser_Q.PageCnt() + 1;

		// 값 넘겨주기
		model.addAttribute("QNum", num);
		model.addAttribute("M_ID", session.getAttribute("mId"));

		return "/QnaWrite";
	}

	// 간편가입 조회
	@RequestMapping("/easySearch")
	public String GoEasySearch(HttpServletRequest request, Model model) {
		if (session.getAttribute("mId") == null)
			return "redirect:/main";

		String kind = request.getParameter("kind");
		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null이면 맨 처음
			pgNum = "1";
		// int형으로
		int pgnum = Integer.parseInt(pgNum);
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();

		map.put("kind", kind);
		map.put("M_ID", session.getAttribute("mId"));

		// 전체 게시글 개수 설정
		pgDTO.setTotalCnt(Ser_SL.PageCnt(map));
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

		map = new HashMap<String, Object>();
		map.put("kind", kind);
		map.put("M_ID", session.getAttribute("mId"));
		map.put("startNum", (pgnum - 1) * pgDTO.getContentNum());
		map.put("ContentNum", pgDTO.getContentNum());

		List<SiteListDTO> dtos = Ser_SL.GetSLList(map);

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

	// 간편가입 탈퇴 내역
	@RequestMapping("/searchLog")
	public String GoSearchLog(HttpServletRequest request, Model model) {
		if (session.getAttribute("mId") == null)
			return "redirect:/main";

		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null이면 맨 처음
			pgNum = "1";
		// int형으로
		int pgnum = Integer.parseInt(pgNum);
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();

		map.put("M_ID", session.getAttribute("mId"));

		// 전체 게시글 개수 설정
		pgDTO.setTotalCnt(Ser_L.PageCnt(map));
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

		map.put("M_ID", session.getAttribute("mId"));
		map.put("startNum", (pgnum - 1) * pgDTO.getContentNum());
		map.put("ContentNum", pgDTO.getContentNum());

		// 리스트 내용
		List<LogDTO> dtos = Ser_L.GetLList(map);

		int first = (pgnum - 1) * pgDTO.getContentNum() + 1;
		int last = first + pgDTO.getContentNum();
		String str = "";
		int j = 0;
		// 각 게시물 번호
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

		if (pgDTO.isPrev()) { // 이전 블록이 존재하는가
			prev = "<";
		}
		if (pgDTO.isNext()) { // 다음 블록이 존재하는가
			next = ">";
		}

		int[] pg;
		// 넘어가서 출력될 페이지 번호들
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

	// 회원 ID, PW 찾기
	@RequestMapping("/viewfindIDPW")
	public String FindIDPW(HttpServletRequest request, Model model) {
		return "/FindIDPW";
	}
}
