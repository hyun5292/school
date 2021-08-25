package ACommand;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.AccountDAO;
import Dto.AccountDTO;

public class ModifyViewCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AccountDAO dao = new AccountDAO();
		String id = request.getParameter("id");
		AccountDTO dto = dao.getMember(id);
		request.setAttribute("dto", dto);
	}
}
