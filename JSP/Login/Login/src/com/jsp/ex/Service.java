package com.jsp.ex;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DaoDto.AccountDTO;

public interface Service {
	public ArrayList<AccountDTO> execute(HttpServletRequest request, HttpServletResponse response);
}
