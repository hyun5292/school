package MCommand;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.MovieDAO;
import Dto.MovieDTO;

public class MovieInfoCommand implements MCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String m_No = request.getParameter("M_no");	//영화 번호 Get
		
		MovieDAO dao = new MovieDAO();	//DAO
		MovieDTO dto = new MovieDTO();	//DTO
		
		dto = dao.getMovie(m_No);	//해당 영화의 정보 Get
		request.setAttribute("dto", dto);	//값 Pass
	}	
}
