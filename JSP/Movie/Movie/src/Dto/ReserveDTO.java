package Dto;

import java.sql.Date;

public class ReserveDTO {

	private String R_no;
	private String ID;
	private int M_no;
	private String theater;
	private Date  R_date;
	private Date R_time;
	private int seat;
	public String getR_no() {
		return R_no;
	}
	public void setR_no(String r_no) {
		R_no = r_no;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getM_no() {
		return M_no;
	}
	public void setM_no(int m_no) {
		M_no = m_no;
	}
	public String getTheater() {
		return theater;
	}
	public void setTheater(String theater) {
		this.theater = theater;
	}
	public Date getR_date() {
		return R_date;
	}
	public void setR_date(Date r_date) {
		R_date = r_date;
	}
	public Date getR_time() {
		return R_time;
	}
	public void setR_time(Date r_time) {
		R_time = r_time;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	
}
