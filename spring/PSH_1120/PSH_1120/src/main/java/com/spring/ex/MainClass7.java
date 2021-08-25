package com.spring.ex;

import java.util.ArrayList;

import com.spring.dao.MemberDAO;
import com.spring.dto.MemberDTO;

public class MainClass7 {
	private static ArrayList<MemberDTO> dtos;
	private static MemberDAO dao = new MemberDAO();
	private static MemberDTO dto = null;
	
	public static void main(String[] args) {
		 dtos = new ArrayList<MemberDTO>();
		 
		 dtos = dao.mSelect();
		 
		 for(int i = 0; i < dtos.size(); i++) {
			 dto = dtos.get(i);
			 System.out.println("-------------------------");
			 /* 교수님이 원하던 거 */
			 System.out.println("ID : " + dtos.get(i).getM_Id());
			 //System.out.println("ID : " + dto.getM_Id());
			 System.out.println("PW : " + dto.getM_Pw());
			 System.out.println("Name : " + dto.getM_Name());
			 System.out.println("Email : " + dto.getM_Email());
		 }
	}
}
