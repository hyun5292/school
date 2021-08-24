package com.ses.dto;

public class LogDTO {
	private int NUM = 0;
	private String M_ID = "";
	private String SL_NAME = "";
	private String SU_KIND = "";
	private String L_ACTIVITY = "";
	private String SL_SITE = "";
	private int L_YEAR = 0;
	private int L_MONTH = 0;
	private int L_DAY = 0;
	private int L_HOUR = 0;
	private int L_MINUTE = 0;
	private String DATE_HOUR = "";
	
	public LogDTO() {}
	
	public LogDTO(String m_ID, String sL_NAME, String sU_KIND, String l_ACTIVITY, String sL_SITE, int l_YEAR, int l_MONTH, int l_DAY,
			int l_HOUR, int l_MINUTE) {
		super();
		M_ID = m_ID;
		SL_NAME = sL_NAME;
		SU_KIND = sU_KIND;
		L_ACTIVITY = l_ACTIVITY;
		SL_SITE = sL_SITE;
		L_YEAR = l_YEAR;
		L_MONTH = l_MONTH;
		L_DAY = l_DAY;
		L_HOUR = l_HOUR;
		L_MINUTE = l_MINUTE;
	}
	

	public int getNUM() {
		return NUM;
	}


	public void setNUM(int nUM) {
		NUM = nUM;
	}


	public String getM_ID() {
		return M_ID;
	}

	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}

	public String getSL_NAME() {
		return SL_NAME;
	}

	public void setSL_NAME(String sL_NAME) {
		SL_NAME = sL_NAME;
	}

	public String getSU_KIND() {
		return SU_KIND;
	}

	public void setSU_KIND(String sU_KIND) {
		SU_KIND = sU_KIND;
	}

	public String getL_ACTIVITY() {
		return L_ACTIVITY;
	}

	public void setL_ACTIVITY(String l_ACTIVITY) {
		L_ACTIVITY = l_ACTIVITY;
	}

	public String getSL_SITE() {
		return SL_SITE;
	}


	public void setSL_SITE(String sL_SITE) {
		SL_SITE = sL_SITE;
	}


	public int getL_YEAR() {
		return L_YEAR;
	}

	public void setL_YEAR(int l_YEAR) {
		L_YEAR = l_YEAR;
	}

	public int getL_MONTH() {
		return L_MONTH;
	}

	public void setL_MONTH(int l_MONTH) {
		L_MONTH = l_MONTH;
	}

	public int getL_DAY() {
		return L_DAY;
	}

	public void setL_DAY(int l_DAY) {
		L_DAY = l_DAY;
	}

	public int getL_HOUR() {
		return L_HOUR;
	}

	public void setL_HOUR(int l_HOUR) {
		L_HOUR = l_HOUR;
	}

	public int getL_MINUTE() {
		return L_MINUTE;
	}

	public void setL_MINUTE(int l_MINUTE) {
		L_MINUTE = l_MINUTE;
	}

	public String getDATE_HOUR() {
		return DATE_HOUR;
	}

	public void setDATE_HOUR(String dATE_HOUR) {
		DATE_HOUR = dATE_HOUR;
	}
	
}
/*
CREATE TABLE `log` (
	`M_ID` VARCHAR(20) NULL DEFAULT NULL,
	`SL_NAME` VARCHAR(20) NULL DEFAULT NULL,
	`SL_SITE` VARCHAR(200) NULL DEFAULT NULL,
	`SU_KIND` VARCHAR(40) NULL DEFAULT NULL,
	`L_ACTIVITY` VARCHAR(20) NULL DEFAULT NULL,
	`L_YEAR` INT(11) NOT NULL DEFAULT 0,
	`L_MONTH` INT(11) NOT NULL DEFAULT 0,
	`L_DAY` INT(11) NOT NULL DEFAULT 0,
	`L_HOUR` INT(11) NOT NULL DEFAULT 0,
	`L_MINUTE` INT(11) NOT NULL DEFAULT 0
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
*/
