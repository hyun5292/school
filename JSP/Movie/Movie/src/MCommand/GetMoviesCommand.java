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
		ArrayList<MovieDTO> dtos = movieDAO.getMovieAll();	//전체 영화 정보 Get
		
		request.setAttribute("dtos", dtos);	//값 Pass
	}
}
