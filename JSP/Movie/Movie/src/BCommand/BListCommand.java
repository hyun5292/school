package BCommand;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BoardDAO;
import Dto.BoardDTO;

public class BListCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pgNum = (String)request.getParameter("pgNum");
		int pg = Integer.parseInt(pgNum);
		
		BoardDAO dao = new BoardDAO();
		int num = dao.getCount();
		int pageNum = num / 10;
		if((num % 10) != 0) {
			pageNum++;
		}
		
		pg -= 1;
		
		int Start = 10 * (pg/10) + 1;
		int End = 10 * ((pg/10) + 1);
		
		ArrayList<BoardDTO> dtos = dao.list(Start + pg*10, (Start + pg*10) + 9);
		
		request.setAttribute("list", dtos);
		request.setAttribute("startPage", Start);
		request.setAttribute("allPage", End);
		request.setAttribute("pg", pageNum);
	}

}
