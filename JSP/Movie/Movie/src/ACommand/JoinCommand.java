package ACommand;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.AccountDAO;
import Dto.AccountDTO;

public class JoinCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AccountDTO dto = new AccountDTO();
		dto.setId(request.getParameter("id"));
		dto.setName(request.getParameter("name"));
		dto.setPw(request.getParameter("pw"));
		dto.setBirth_y(request.getParameter("birth_y"));
		dto.setBirth_m(request.getParameter("birth_m"));
		dto.setBirth_d(request.getParameter("birth_d"));
		dto.setPhone1(request.getParameter("phone1"));
		dto.setPhone2(request.getParameter("phone2"));
		dto.setPhone3(request.getParameter("phone3"));

		AccountDAO dao = AccountDAO.getInstance();
		if (dao.confirmId(dto.getId()) == AccountDAO.ACCOUNT_EXISTENT) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('ID is already exists');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			int ri = dao.insertMember(dto);
			if (ri != AccountDAO.ACCOUNT_JOIN_SUCCESS) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('Join Fail');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
			}
		}
	}
}
