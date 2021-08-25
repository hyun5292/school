package BCommand;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BoardDAO;
import Dto.BoardDTO;

public class BPageNumCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pgNum = (String) request.getParameter("pgNum");
		int pg = Integer.parseInt(pgNum);

		BoardDAO dao = new BoardDAO();
		int num = dao.getCount();
		if (pg > num) {
			pg = num;
		}

		int pageNum = num / 10;
		if ((num % 10) != 0) {
			pageNum++;
		}

		pg /= 10;

		int Start = 10 * pg + 1;
		int End = 10 * (pg + 1);
		System.out.println(Start);
		System.out.println(End);

		ArrayList<BoardDTO> dtos = dao.list(Start, End);

		request.setAttribute("list", dtos);
		request.setAttribute("startPage", Start);
		request.setAttribute("allPage", End);
	}
}
