package MCommand;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.MovieDAO;
import Dto.MovieDTO;

public class GetMoviesCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MovieDAO movieDAO = new MovieDAO();	//DAO
		ArrayList<MovieDTO> dtos = movieDAO.getMovieAll();	//��ü ��ȭ ���� Get
		
		request.setAttribute("dtos", dtos);	//�� Pass
	}
}
