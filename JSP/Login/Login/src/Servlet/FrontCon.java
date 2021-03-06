package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Provider.Service;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DaoDto.AccountDAO;
import DaoDto.AccountDTO;
import Service.AccountAllService;
import Service.MemberService;

/**
 * Servlet implementation class FrontCon
 */
@WebServlet("*.do")
public class FrontCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontCon() {
		super();
		service = new MemberService();
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
		System.out.println("actionDo()");

		String uri = request.getRequestURI();
		String conpath = request.getContextPath();
		String command = uri.substring(conpath.length());

		if (command.equals("/accountAll.do")) {
			response.setContentType("text/html; charset=EUC-kr");
			PrintWriter writer = response.getWriter();
			writer.println("<html><head></head><body>");

			AccountAllService service = new AccountAllService();
			ArrayList<AccountDTO> dtos = service.execute(request, response);

			for (int i = 0; i < dtos.size(); i++) {
				AccountDTO dto = dtos.get(i);

				String id = dto.getId();
				String name = dto.getName();

				writer.println("<h1>" + (i + 1) + "?? <br/><h1>");
				writer.println("?????? : " + id + " ???? : " + name);
				writer.println("<br/>");
			}

			writer.println("</body></html>");
		} else if (command.equals("/Login.do")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");

			if (service.login(id, pw)) {
				request.setAttribute("msg", "??????????");
				request.setAttribute("id", id);
			} else {
				request.setAttribute("msg", "?????? ????");
			}
			request.getRequestDispatcher("LoginResult.jsp").forward(request, response);

		} else if (uri.equals(conpath + "/ModifyForm.do")) {
			String id = request.getParameter("id");
			AccountDTO dto = service.getMember(id);
			request.setAttribute("dto", dto);
			request.getRequestDispatcher("Modify.jsp").forward(request, response);
		
		} else if (uri.equals(conpath + "/Modify.do")) {
			AccountDTO dto = new AccountDTO();
			dto.setId(request.getParameter("id"));
			dto.setPw(request.getParameter("pw"));
			dto.setName(request.getParameter("name"));
			dto.setPhone1(request.getParameter("phone1"));
			dto.setPhone2(request.getParameter("phone2"));
			dto.setPhone3(request.getParameter("phone3"));
			dto.setGender(request.getParameter("gender"));
			dto.setNickname(request.getParameter("nickname"));
			dto.setBirth_y(request.getParameter("birth_y"));
			dto.setBirth_m(request.getParameter("birth_m"));
			dto.setBirth_d(request.getParameter("birth_d"));
			dto.setEmail1(request.getParameter("email1"));
			dto.setEmail2(request.getParameter("email2"));

			boolean rs = service.update(dto);
			if (rs) {
				request.setAttribute("msg", "??????????????");
				request.setAttribute("id", dto.getId());
			} else {
				request.setAttribute("msg", "???? ????");
			}
			request.getRequestDispatcher("ModifyResult.jsp").forward(request, response);
		} else if (uri.equals(conpath + "/Join.do")) {
			AccountDTO dto = new AccountDTO();
			dto.setId(request.getParameter("id"));
			dto.setPw(request.getParameter("pw"));
			dto.setName(request.getParameter("name"));
			dto.setPhone1(request.getParameter("phone1"));
			dto.setPhone2(request.getParameter("phone2"));
			dto.setPhone3(request.getParameter("phone3"));
			dto.setGender(request.getParameter("gender"));
			dto.setNickname(request.getParameter("nickname"));
			dto.setBirth_y(request.getParameter("birth_y"));
			dto.setBirth_m(request.getParameter("birth_m"));
			dto.setBirth_d(request.getParameter("birth_d"));
			dto.setEmail1(request.getParameter("email1"));
			dto.setEmail2(request.getParameter("email2"));

			AccountDAO dao = AccountDAO.getInstance();
			if (dao.confirmId(dto.getId()) == AccountDAO.ACCOUNT_EXISTENT) {
				request.setAttribute("msg", "?????? ????");
				request.getRequestDispatcher("JoinResult.jsp").forward(request, response);
			} else {
				int ri = dao.insertMember(dto);
				if (ri == AccountDAO.ACCOUNT_JOIN_SUCCESS) {
					request.setAttribute("msg", "??????????");
				} else {
					request.setAttribute("msg", "???????? ????");
				}
				request.getRequestDispatcher("JoinResult.jsp").forward(request, response);
			}
		}
	}
}
