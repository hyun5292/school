package MCommand;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckSelectedCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String userID = request.getParameter("userID");
		String theater = request.getParameter("theater");
		String m_No = request.getParameter("m_No");
		String r_Date = request.getParameter("r_Date");
		String r_Time = request.getParameter("r_Time");

		if (m_No == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Please Choose Moive');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			request.setAttribute("userID", userID);
			request.setAttribute("theater", theater);
			request.setAttribute("m_No", m_No);
			request.setAttribute("r_Date", r_Date);
			request.setAttribute("r_Time", r_Time);
		}
	}
}
