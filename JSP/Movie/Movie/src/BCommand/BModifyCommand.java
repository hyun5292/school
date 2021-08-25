package BCommand;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BoardDAO;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");

		if (bId == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('오류가 발생하였습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else if (bName == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('오류가 발생하였습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else if (bTitle == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('제목을 입력해주세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else if (bContent == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('내용을 입력해주세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			BoardDAO dao = new BoardDAO();
			dao.modify(bId, bName, bTitle, bContent);
		}
	}

}
