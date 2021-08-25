package com.spring.dto;

public class MemberDTO {
	private String M_Id = null;
	private String M_Pw = null;
	private String M_Name = null;
	private String M_Email = null;
	
	public String getM_Id() {
		return M_Id;
	}
	public MemberDTO(String m_Id, String m_Pw, String m_Name, String m_Email) {
		super();
		M_Id = m_Id;
		M_Pw = m_Pw;
		M_Name = m_Name;
		M_Email = m_Email;
	}
	public void setM_Id(String m_Id) {
		M_Id = m_Id;
	}
	public String getM_Pw() {
		return M_Pw;
	}
	public void setM_Pw(String m_Pw) {
		M_Pw = m_Pw;
	}
	public String getM_Name() {
		return M_Name;
	}
	public void setM_Name(String m_Name) {
		M_Name = m_Name;
	}
	public String getM_Email() {
		return M_Email;
	}
	public void setM_Email(String m_Email) {
		M_Email = m_Email;
	}
	
	
}
