package BCommand;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import Dao.BoardDAO;
import Dto.BoardDTO;

public class BReplyViewCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bId = request.getParameter("bId");
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.replyView(bId);
		
		request.setAttribute("replyView", dto);
	}

}
