package MCommand;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ReserveDAO;

public class ReserveCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userID = request.getParameter("userID");
		String THEATER = request.getParameter("theater");
		String M_NO = request.getParameter("m_No");
		String R_DATE = request.getParameter("r_Date");
		String R_TIME = request.getParameter("r_Time");
		String seat;
		ReserveDAO movieDAO = new ReserveDAO();
		String[] values = request.getParameterValues("seat");

		if (values == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Please Choose Seat');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			for (int i = 0; i < values.length; i++) {
				seat = values[i].substring(values[i].length() - 2);
				int result = movieDAO.ticketing(values[i], userID, M_NO, THEATER, R_DATE, R_TIME, seat);
				if (1 == result) {
					continue;
				} else if (0 == result) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('Reserve Failed');");
					script.println("history.back();");
					script.println("</script>");
					script.close();
				} else if (-1 == result) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('DB Error');");
					script.println("history.back();");
					script.println("</script>");
					script.close();
				}
			}
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Reserve Success');");
			script.println("location.href='Main.jsp'"); //여기 수정해야함
			script.println("</script>");
			script.close();
		}
	}
}
