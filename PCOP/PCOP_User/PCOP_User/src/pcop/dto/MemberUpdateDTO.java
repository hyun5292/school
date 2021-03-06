package pcop.dto;

import java.sql.Blob;

public class MemberUpdateDTO {
	private String M_ID;
    private String M_NAME;
    private String M_PW;
    private String M_TEL1;
    private String M_TEL2;
    private String M_TEL3;
    private String M_BIRTH1;
    private String M_BIRTH2;
    private String M_BIRTH3;
    private String M_GENDER;  
    private String BEGIN_TIME; //시작시간
    private String REMAIN_TIME; //남은시간
    private String USED_MONEY; //사용요금
    private int S_NUM; //좌석번호
    
    public MemberUpdateDTO() {
    	super();
    }
    
    /** getter, setter 설정 **/
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
	
	public String getM_BIRTH1() {
		return M_BIRTH1;
	}
	
	public void setM_BIRTH1(String m_BIRTH1) {
		M_BIRTH1 = m_BIRTH1;
	}
	
	public String getM_BIRTH2() {
		return M_BIRTH2;
	}
	
	public void setM_BIRTH2(String m_BIRTH2) {
		M_BIRTH2 = m_BIRTH2;
	}
	
	public String getM_BIRTH3() {
		return M_BIRTH3;
	}
	
	public void setM_BIRTH3(String m_BIRTH3) {
		M_BIRTH3 = m_BIRTH3;
	}
	
	public String getM_GENDER() {
		return M_GENDER;
	}
	
	public void setM_GENDER(String m_GENDER) {
		M_GENDER = m_GENDER;
	}	
	
	public String getBEGIN_TIME() {
		return BEGIN_TIME;
	}
	
	public String setBEGIN_TIME(String bEGIN_TIME) {
		return BEGIN_TIME = bEGIN_TIME;
	}
	
	public String getREMAIN_TIME() {
		return REMAIN_TIME;
	}
	
	public void setREMAIN_TIME(String rEMAIN_TIME) {
		REMAIN_TIME = rEMAIN_TIME;
	}
	public String getUSED_MONEY() {
		return USED_MONEY;
	}
	
	public void setUSED_MONEY(String uSED_MONEY) {
		USED_MONEY = uSED_MONEY;
	}
	
	public int getS_NUM() {
		return S_NUM;
	}

	public void setS_NUM(int s_NUM) {
		S_NUM = s_NUM;
	}

	@Override
	public String toString() {
		return "MemberUpdateDTO [M_ID=" + M_ID + ", M_NAME=" + M_NAME + ", M_PW=" + M_PW + ", M_TEL1=" + M_TEL1
				+ ", M_TEL2=" + M_TEL2 + ", M_TEL3=" + M_TEL3 + ", M_BIRTH1=" + M_BIRTH1 + ", M_BIRTH2=" + M_BIRTH2
				+ ", M_BIRTH3=" + M_BIRTH3 + ", M_GENDER=" + M_GENDER + ", BEGIN_TIME=" + BEGIN_TIME + ", REMAIN_TIME="
				+ REMAIN_TIME + ", USED_MONEY=" + USED_MONEY + ", S_NUM=" + S_NUM + "]";
	}
}
