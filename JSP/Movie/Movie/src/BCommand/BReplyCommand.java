package BCommand;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import Dao.BoardDAO;

public class BReplyCommand implements BCommand {
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bDate = sf.format(nowTime);
		String bHit = request.getParameter("bHit");
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		String bIndent = request.getParameter("bIndent");

		if (bId == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('There's no Id');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else if (bName == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('There's no Name');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else if (bTitle == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('There's no Title');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else if (bContent == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('There's no Content');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			BoardDAO dao = new BoardDAO();
			dao.reply(Integer.parseInt(bId), bTitle, bName, bDate, bContent, bHit, bGroup, bStep, bIndent);
			
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Reply Success');");
			script.println("location.href='list.do?pgNum=1'");
			script.println("</script>");
			script.close();
		}
	}

}
