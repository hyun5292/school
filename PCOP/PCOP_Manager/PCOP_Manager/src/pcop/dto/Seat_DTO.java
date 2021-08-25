package pcop.dto;

public class Seat_DTO {
	private int S_NUM;
	private String M_ID;
	private String S_USE;
	private String S_FIX;
	private String S_CLEAN;
	
	public Seat_DTO() {
		
	}
	
	public Seat_DTO(int s_NUM, String m_ID, String s_USE, String s_FIX, String s_CLEAN) {
		super();
		S_NUM = s_NUM;
		M_ID = m_ID;
		S_USE = s_USE;
		S_FIX = s_FIX;
		S_CLEAN = s_CLEAN;
	}
	public int getS_NUM() {
		return S_NUM;
	}
	public void setS_NUM(int s_NUM) {
		S_NUM = s_NUM;
	}
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}
	public String getS_USE() {
		return S_USE;
	}
	public void setS_USE(String s_USE) {
		S_USE = s_USE;
	}
	public String getS_FIX() {
		return S_FIX;
	}
	public void setS_FIX(String s_FIX) {
		S_FIX = s_FIX;
	}
	public String getS_CLEAN() {
		return S_CLEAN;
	}
	public void setS_CLEAN(String s_CLEAN) {
		S_CLEAN = s_CLEAN;
	}
	
	
}
