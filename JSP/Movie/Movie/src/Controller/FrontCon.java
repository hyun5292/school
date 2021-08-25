package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import ACommand.*;
import BCommand.*;
import MCommand.*;

/**
 * Servlet implementation class FrontCon
 */
@WebServlet("*.do")
public class FrontCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontCon() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");

		String viewPage = null;
		BCommand Bcommand = null;
		MCommand Mcommand = null;
		ACommand Acommand = null;

		String uri = request.getRequestURI();
		String conpath = request.getContextPath();
		String com = uri.substring(conpath.length());

		// 영화 소개
		if (com.equals("/Movies.do")) { // 영화 소개 목록
			Mcommand = new GetMoviesCommand();
			Mcommand.execute(request, response);
			viewPage = "Movie.jsp";
		} else if (com.equals("/MovieInfo.do")) { // 영화 상세 정보
			Mcommand = new MovieInfoCommand();
			Mcommand.execute(request, response);
			viewPage = "MovieInfo.jsp";
		}
		// 영화 예매
		else if (com.equals("/CheckSelect.do")) { // 영화 정보 선택 체크
			Mcommand = new CheckSelectedCommand();
			Mcommand.execute(request, response);
			viewPage = "movieseat.jsp";
		} else if (com.equals("/reserve.do")) { // 영화 예매
			Mcommand = new ReserveCommand();
			Mcommand.execute(request, response);
			viewPage = "moviechoice.jsp";
		}
		// 게시판
		else if (com.equals("/list.do")) { // 게시판 리스트 출력
			Bcommand = new BListCommand();
			Bcommand.execute(request, response);
			viewPage = "List.jsp";
		} else if (com.equals("/writeView.do")) { // 게시판 글 작성 뷰
			viewPage = "writeView.jsp";
		} else if (com.equals("/write.do")) { // 글 DB 저장
			Bcommand = new BWriteCommand();
			Bcommand.execute(request, response);
			viewPage = "list.do?pgNum=1";
		} else if (com.equals("/contentView.do")) { // 게시판 제목 클릭 시 상세 보기
			Bcommand = new BContentCommand();
			Bcommand.execute(request, response);
			viewPage = "contentView.jsp";
		} else if (com.equals("/delete.do")) { // 게시판 게시물 삭제
			Bcommand = new BDeleteCommand();
			Bcommand.execute(request, response);
			viewPage = "list.do?pgNum=1";
		} else if (com.equals("/modify.do")) { // 게시판 게시물 수정
			Bcommand = new BModifyCommand();
			Bcommand.execute(request, response);
			viewPage = "list.do?pgNum=1";
		} else if (com.equals("/replyView.do")) { // 게시물 댓글 뷰
			Bcommand = new BReplyViewCommand();
			Bcommand.execute(request, response);
			viewPage = "replyView.jsp";
		} else if (com.equals("/reply.do")) { // 게시물 댓글 DB 저장
			Bcommand = new BReplyCommand();
			Bcommand.execute(request, response);
			viewPage = "list.do?pgNum=1";
		}
		// 마이페이지 뷰
		else if (com.equals("/MyPageView.do")) {
			viewPage = "myPage.jsp";
		}
		// 로그인
		else if (com.equals("/Login.do")) {
			Acommand = new LoginCommand();
			Acommand.execute(request, response);
			viewPage = "Main.jsp";
		}
		// 회원가입
		else if (com.equals("/Join.do")) {
			Acommand = new JoinCommand();
			Acommand.execute(request, response);
			viewPage = "Login.html";
		}
		// 회원 정보 수정 뷰
		else if (com.equals("/ModifyForm.do")) {
			Acommand = new ModifyViewCommand();
			Acommand.execute(request, response);
			viewPage = "Modify.jsp";
		}
		// 회원 정보 수정
		else if (com.equals("/Modify.do")) {
			Acommand = new ModifyCommand();
			Acommand.execute(request, response);
			viewPage = "Main.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
