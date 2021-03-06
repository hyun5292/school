package com.spring.hamsamo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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

import com.spring.hamsamo.dto.BoardDTO;
import com.spring.hamsamo.dto.PageDTO;
import com.spring.hamsamo.service.BoardService;

@Controller
@Repository(value = "/board")
public class BoardController {
	@Autowired
	BoardService bService;

	@Inject
	HttpSession session;

	// 게시물 뷰
	@RequestMapping("/contentForm")
	public String BContentForm(HttpServletRequest request, Model model) {
		System.out.println("Controller - ContentForm()");
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();

		// 수정 후에 돌아오는 경우 아니면
		if (request.getParameter("bHit") != null) {
			map.put("bHit", Integer.parseInt(request.getParameter("bHit")) + 1);
			map.put("bId", request.getParameter("bId"));

			bService.UpHit(map);
		}

		// 내용 받아와
		BoardDTO dto = bService.GetBContent(request.getParameter("bId"));
		// bId랑은 다르게 겉으로 보여주는 번호
		dto.setbNum(dto.getbGroup());

		List<BoardDTO> replies = bService.GetReplies(dto.getbGroup());

		// 화면에 값을 넘겨줘 dto에 dto를,
		model.addAttribute("dto", dto);
		model.addAttribute("replies", replies);

		return "/board/contentForm";
	}

	// 게시물 작성 뷰 - writeForm
	@RequestMapping("/writeForm")
	public String BWriteForm(Model model) {
		System.out.println("Controller - WriteForm()");
		// 작성 날짜 넣으려고 timestamp 작성한 거
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		int bNum = bService.PageCnt() + 1;
		String today = ts.getYear() + 1900 + "-" + ts.getMonth() + "-" + ts.getDate();
		String mId = session.getAttribute("mId").toString();

		model.addAttribute("bNum", bNum);
		model.addAttribute("bDate", today);

		return "/board/writeForm";
	}

	// 게시물 작성
	@RequestMapping("/write")
	public String BWrite(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		System.out.println("Controller - Write()");

		// 작성 날짜 넣으려고 timestamp 작성한 거
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, String> map = new HashMap<String, String>();

		map.put("mId", session.getAttribute("mId").toString());
		map.put("bTitle", request.getParameter("bTitle"));
		map.put("bContent", request.getParameter("bContent"));
		map.put("bDate", ts.toString());
		boolean chk = bService.BWrite(map);

		if (chk) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('작성이 완료되었습니다'); document.location.href='list'</script>");
			out.flush();
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
			out.flush();
		}
		return "/list";
	}

	// 게시물 수정 폼
	@RequestMapping("/modifyForm")
	public String BModifyForm(HttpServletResponse response, HttpServletRequest request, Model model)
			throws IOException {
		System.out.println("Controller - ModifyForm()");

		if (request.getParameter("mId").equals(request.getParameter("BmId"))) {
			BoardDTO dto = bService.GetBContent(request.getParameter("bId"));
			dto.setbNum(dto.getbGroup());

			model.addAttribute("dto", dto);

			return "/board/modifyForm";
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('작성자만 수정할 수 있습니다!!'); history.go(-1);</script>");
			out.flush();

			return "board/contentForm?bId=" + request.getParameter("bId");
		}
	}

	// 게시물 수정
	@RequestMapping("/modify")
	public String BModify(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		System.out.println("Controller - Modify()");

		// 작성 날짜 넣으려고 timestamp 작성한 거
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, String> map = new HashMap<String, String>();

		map.put("mId", session.getAttribute("mId").toString());
		map.put("bTitle", request.getParameter("bTitle"));
		map.put("bContent", request.getParameter("bContent"));
		map.put("bDate", ts.toString());
		map.put("bId", request.getParameter("bId"));

		boolean chk = bService.BModify(map);

		if (chk) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('수정이 완료되었습니다'); document.location.href='contentForm?bId="
					+ request.getParameter("bId") + "'</script>");
			out.flush();
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
			out.flush();
		}
		return "board/contentForm?bId=" + request.getParameter("bId");
	}

	// 게시물 삭제
	@RequestMapping("/delete")
	public String BDelete(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		System.out.println("Controller - Delete()");

		if (request.getParameter("mId").equals(request.getParameter("BmId"))) {
			boolean chk = bService.BDelete(request.getParameter("bGroup"));

			if (chk) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('삭제되었습니다'); document.location.href='list';</script>");
				out.flush();
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('삭제되었습니다'); document.location.href='list';</script>");
				out.flush();
			}

			return "list";
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('작성자만 삭제할 수 있습니다!!'); history.go(-1);</script>");
			out.flush();

			return "board/contentForm?bId=" + request.getParameter("bId");
		}
	}

	// 댓글 폼 보여주기
	@RequestMapping("/replyForm")
	public String BReplyForm(HttpServletRequest request, Model model) {
		System.out.println("Controller - replyForm()");
		BoardDTO dto = bService.GetBContent(request.getParameter("bId"));

		model.addAttribute("dto", dto);

		return "/board/replyForm";
	}

	// 게시물 댓글 DB에 넣기
	@RequestMapping("/reply")
	public String BReply(HttpServletRequest request, Model model) {
		System.out.println("Controller - Reply()");
		// 작성 날짜 넣으려고 timestamp 작성한 거
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("mId", session.getAttribute("mId").toString());
		map.put("bTitle", "댓글");
		map.put("bContent", request.getParameter("bReply"));
		map.put("bDate", ts.toString());
		map.put("bGroup", request.getParameter("bGroup"));
		bService.BReply(map);

		return "redirect:contentForm?bId=" + request.getParameter("bId");
	}

	// 게시물 댓글 DB에 넣기
	@RequestMapping("/rereply")
	public String BReReply(HttpServletRequest request, Model model) {
		System.out.println("Controller - ReReply()");
		// 작성 날짜 넣으려고 timestamp 작성한 거
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();

		int bStep = Integer.parseInt(request.getParameter("bStep"));
		int bIndent = Integer.parseInt(request.getParameter("bIndent"));

		bService.ReplyReset(bStep);
		map.put("mId", session.getAttribute("mId").toString());
		map.put("bTitle", "댓글");
		map.put("bContent", request.getParameter("bRReply"));
		map.put("bDate", ts.toString());
		map.put("bGroup", request.getParameter("bGroup"));
		map.put("bStep", bStep + 1);
		map.put("bIndent", bIndent + 1);

		bService.BRReply(map);

		return "redirect:contentForm?bId=" + request.getParameter("bId");
	}

	// 게시물 댓글 삭제
	@RequestMapping("/replyDelete")
	public String BReplyDelete(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		System.out.println("Controller - ReplyDelete()");
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();

		if (session.getAttribute("mId").equals(request.getParameter("BmId"))) {
			map.put("bGroup", request.getParameter("bGroup"));
			map.put("bStep", request.getParameter("bStep"));
			map.put("bIndent", request.getParameter("bIndent"));

			bService.BReplyDelete(map);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('삭제되었습니다'); document.location.href='contentForm?bId=" + request.getParameter("bId") + "'</script>");
			out.flush();
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('작성자만 삭제할 수 있습니다!!'); document.location.href='contentForm?bId=" + request.getParameter("bId") + "'</script>");
			out.flush();
		}
		return "board/contentForm?bId=" + request.getParameter("bId");
	}
}
