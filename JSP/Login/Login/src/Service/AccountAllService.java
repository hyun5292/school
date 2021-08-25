package Service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.ex.Service;

import DaoDto.AccountDAO;
import DaoDto.AccountDTO;

public class AccountAllService implements Service{
	public ArrayList<AccountDTO> execute(HttpServletRequest request, HttpServletResponse response){
		AccountDAO dao = AccountDAO.getInstance();
		return dao.accountAll();
	}
}
