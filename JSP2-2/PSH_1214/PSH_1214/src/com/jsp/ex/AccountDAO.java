package com.jsp.ex;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AccountDAO {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String uid = "clara";
	private String upw = "agnes";
	
	public AccountDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<AccountDTO> accountSelect(){
		ArrayList<AccountDTO> dtos = new ArrayList<AccountDTO>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
		}
	}
}
