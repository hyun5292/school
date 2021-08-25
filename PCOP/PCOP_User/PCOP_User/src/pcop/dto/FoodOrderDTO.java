package pcop.dto;

import java.sql.Blob;

public class FoodOrderDTO {
	private String M_ID;
    private int S_NUM; //좌석번호
    private String FO_PRODUCT;
    private int FO_PRICE;
    private int FO_COUNT; //수량
    private String FO_WAY;
    private String FO_RESULT;
    private String FO_MEMO;
    private int FO_NUM; //주문개수
    private String FS_IMG;
    
    /** getter, setter 설정 **/
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}
	public int getS_NUM() {
		return S_NUM;
	}
	public void setS_NUM(int s_NUM) {
		S_NUM = s_NUM;
	}
	public String getFO_PRODUCT() {
		return FO_PRODUCT;
	}
	public void setFO_PRODUCT(String fO_PRODUCT) {
		FO_PRODUCT = fO_PRODUCT;
	}
	public int getFO_PRICE() {
		return FO_PRICE;
	}
	public void setFO_PRICE(int fO_PRICE) {
		FO_PRICE = fO_PRICE;
	}
	public int getFO_COUNT() {
		return FO_COUNT;
	}
	public void setFO_COUNT(int fO_COUNT) {
		FO_COUNT = fO_COUNT;
	}
	public String getFO_WAY() {
		return FO_WAY;
	}
	public void setFO_WAY(String fO_WAY) {
		FO_WAY = fO_WAY;
	}
	public String getFO_RESULT() {
		return FO_RESULT;
	}
	public void setFO_RESULT(String fO_RESULT) {
		FO_RESULT = fO_RESULT;
	}
	public String getFO_MEMO() {
		return FO_MEMO;
	}
	public void setFO_MEMO(String fO_MEMO) {
		FO_MEMO = fO_MEMO;
	}
	public int getFO_NUM() {
		return FO_NUM;
	}
	public void setFO_NUM(int fO_NUM) {
		FO_NUM = fO_NUM;
	}
	
	public String getFS_IMG() {
		return FS_IMG;
	}
	public void setFS_IMG(String fS_IMG) {
		FS_IMG = fS_IMG;
	}
	
	@Override
	public String toString() {
		return "FoodOrderDTO [M_ID=" + M_ID + ", S_NUM=" + S_NUM + ", FO_PRODUCT=" + FO_PRODUCT + ", FO_PRICE="
				+ FO_PRICE + ", FO_COUNT=" + FO_COUNT + ", FO_WAY=" + FO_WAY + ", FO_RESULT=" + FO_RESULT + ", FO_MEMO="
				+ FO_MEMO + ", FO_NUM=" + FO_NUM + ", FS_IMG=" + FS_IMG + "]";
	}
}
