package ACommand;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.AccountDAO;

public class LoginCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		AccountDAO dao = new AccountDAO();

		int Chk = dao.userCheck(id, pw);
		if (Chk == AccountDAO.ACCOUNT_LOGIN_SUCCESS) {
			request.setAttribute("id", id);
			request.setAttribute("pw", pw);
		} else {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Check ID or PW');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
	}
}
