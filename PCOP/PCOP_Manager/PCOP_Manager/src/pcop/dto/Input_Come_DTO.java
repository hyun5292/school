package pcop.dto;

import java.sql.Timestamp;

public class Input_Come_DTO {
	private String C_L_KIND;
	private String C_S_KIND;
	private String C_DATE;
	private int C_PRICE;
	private String C_MEMO;
	
	public Input_Come_DTO() {
		
	}

	public String getC_L_KIND() {
		return C_L_KIND;
	}

	public void setC_L_KIND(String c_L_KIND) {
		C_L_KIND = c_L_KIND;
	}
	
	public String getC_S_KIND() {
		return C_S_KIND;
	}

	public void setC_S_KIND(String c_S_KIND) {
		C_S_KIND = c_S_KIND;
	}

	public String getC_DATE() {
		return C_DATE;
	}

	public void setC_DATE(String c_DATE) {
		C_DATE = c_DATE;
	}

	public int getC_PRICE() {
		return C_PRICE;
	}

	public void setC_PRICE(int c_PRICE) {
		C_PRICE = c_PRICE;
	}

	public String getC_MEMO() {
		return C_MEMO;
	}

	public void setC_MEMO(String c_MEMO) {
		C_MEMO = c_MEMO;
	}
	
	
}
