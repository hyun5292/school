package BCommand;

import java.util.Date;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BoardDAO;

public class BWriteCommand implements BCommand {
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String bTitle = request.getParameter("bTitle");
		String bName = request.getParameter("bName");
		String bDate = sf.format(nowTime);
		String bContent = request.getParameter("bContent"); 
		
		if(bTitle == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('제목을 입력해주세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else if(bName == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('작성자를 입력해주세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else if(bContent == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('내용을 입력해주세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		BoardDAO dao = new BoardDAO();
		boolean rs = dao.write(bTitle, bName, bDate, bContent);	
	}
}
