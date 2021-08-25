package pcop.dto;

public class Member_DTO {
	private String M_ID;
	private String M_NAME;
	private String M_PW;
	private String M_TEL1;
	private String M_TEL2;
	private String M_TEL3;
	private String M_BIRTH;
	private String M_GENDER;
	
	public Member_DTO() {
		
	}
	

	public Member_DTO(String m_ID, String m_NAME, String m_PW, String m_TEL1, String m_TEL2, String m_TEL3,
			String m_BIRTH, String m_GENDER) {
		super();
		M_ID = m_ID;
		M_NAME = m_NAME;
		M_PW = m_PW;
		M_TEL1 = m_TEL1;
		M_TEL2 = m_TEL2;
		M_TEL3 = m_TEL3;
		M_BIRTH = m_BIRTH;
		M_GENDER = m_GENDER;
	}


	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}
	public String getM_NAME() {
		return M_NAME;
	}
	public void setM_NAME(String m_NAME) {
		M_NAME = m_NAME;
	}
	public String getM_PW() {
		return M_PW;
	}
	public void setM_PW(String m_PW) {
		M_PW = m_PW;
	}
	public String getM_TEL1() {
		return M_TEL1;
	}
	public void setM_TEL1(String m_TEL1) {
		M_TEL1 = m_TEL1;
	}
	public String getM_TEL2() {
		return M_TEL2;
	}
	public void setM_TEL2(String m_TEL2) {
		M_TEL2 = m_TEL2;
	}
	public String getM_TEL3() {
		return M_TEL3;
	}
	public void setM_TEL3(String m_TEL3) {
		M_TEL3 = m_TEL3;
	}
	public String getM_BIRTH() {
		return M_BIRTH;
	}
	public void setM_BIRTH(String m_BIRTH) {
		M_BIRTH = m_BIRTH;
	}
	public String getM_GENDER() {
		return M_GENDER;
	}
	public void setM_GENDER(String m_GENDER) {
		M_GENDER = m_GENDER;
	}
}
