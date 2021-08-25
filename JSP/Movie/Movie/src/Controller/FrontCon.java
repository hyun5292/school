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

		// ��ȭ �Ұ�
		if (com.equals("/Movies.do")) { // ��ȭ �Ұ� ���
			Mcommand = new GetMoviesCommand();
			Mcommand.execute(request, response);
			viewPage = "Movie.jsp";
		} else if (com.equals("/MovieInfo.do")) { // ��ȭ �� ����
			Mcommand = new MovieInfoCommand();
			Mcommand.execute(request, response);
			viewPage = "MovieInfo.jsp";
		}
		// ��ȭ ����
		else if (com.equals("/CheckSelect.do")) { // ��ȭ ���� ���� üũ
			Mcommand = new CheckSelectedCommand();
			Mcommand.execute(request, response);
			viewPage = "movieseat.jsp";
		} else if (com.equals("/reserve.do")) { // ��ȭ ����
			Mcommand = new ReserveCommand();
			Mcommand.execute(request, response);
			viewPage = "moviechoice.jsp";
		}
		// �Խ���
		else if (com.equals("/list.do")) { // �Խ��� ����Ʈ ���
			Bcommand = new BListCommand();
			Bcommand.execute(request, response);
			viewPage = "List.jsp";
		} else if (com.equals("/writeView.do")) { // �Խ��� �� �ۼ� ��
			viewPage = "writeView.jsp";
		} else if (com.equals("/write.do")) { // �� DB ����
			Bcommand = new BWriteCommand();
			Bcommand.execute(request, response);
			viewPage = "list.do?pgNum=1";
		} else if (com.equals("/contentView.do")) { // �Խ��� ���� Ŭ�� �� �� ����
			Bcommand = new BContentCommand();
			Bcommand.execute(request, response);
			viewPage = "contentView.jsp";
		} else if (com.equals("/delete.do")) { // �Խ��� �Խù� ����
			Bcommand = new BDeleteCommand();
			Bcommand.execute(request, response);
			viewPage = "list.do?pgNum=1";
		} else if (com.equals("/modify.do")) { // �Խ��� �Խù� ����
			Bcommand = new BModifyCommand();
			Bcommand.execute(request, response);
			viewPage = "list.do?pgNum=1";
		} else if (com.equals("/replyView.do")) { // �Խù� ��� ��
			Bcommand = new BReplyViewCommand();
			Bcommand.execute(request, response);
			viewPage = "replyView.jsp";
		} else if (com.equals("/reply.do")) { // �Խù� ��� DB ����
			Bcommand = new BReplyCommand();
			Bcommand.execute(request, response);
			viewPage = "list.do?pgNum=1";
		}
		// ���������� ��
		else if (com.equals("/MyPageView.do")) {
			viewPage = "myPage.jsp";
		}
		// �α���
		else if (com.equals("/Login.do")) {
			Acommand = new LoginCommand();
			Acommand.execute(request, response);
			viewPage = "Main.jsp";
		}
		// ȸ������
		else if (com.equals("/Join.do")) {
			Acommand = new JoinCommand();
			Acommand.execute(request, response);
			viewPage = "Login.html";
		}
		// ȸ�� ���� ���� ��
		else if (com.equals("/ModifyForm.do")) {
			Acommand = new ModifyViewCommand();
			Acommand.execute(request, response);
			viewPage = "Modify.jsp";
		}
		// ȸ�� ���� ����
		else if (com.equals("/Modify.do")) {
			Acommand = new ModifyCommand();
			Acommand.execute(request, response);
			viewPage = "Main.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
