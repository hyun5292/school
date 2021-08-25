package MCommand;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.MovieDAO;
import Dto.MovieDTO;

public class MovieInfoCommand implements MCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String m_No = request.getParameter("M_no");	//��ȭ ��ȣ Get
		
		MovieDAO dao = new MovieDAO();	//DAO
		MovieDTO dto = new MovieDTO();	//DTO
		
		dto = dao.getMovie(m_No);	//�ش� ��ȭ�� ���� Get
		request.setAttribute("dto", dto);	//�� Pass
	}	
}
