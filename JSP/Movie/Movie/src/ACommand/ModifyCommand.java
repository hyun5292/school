package ACommand;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.AccountDAO;
import Dto.AccountDTO;

public class ModifyCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AccountDAO dao = new AccountDAO();
		
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

		int rs = dao.updateMember(dto);
		if (rs == dao.ACCOUNT_MODIFY_SUCCESS) {
			request.setAttribute("id", dto.getId());
			request.setAttribute("pw", dto.getPw());
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Modify Success.');");
			script.println("location.href='Main.jsp'");
			script.println("</script>");
			script.close();
		} else {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Modify Fail');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
	}
}
