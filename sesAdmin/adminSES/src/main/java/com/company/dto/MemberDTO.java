package com.company.dto;

public class MemberDTO {
	private int NUM = 0;
	private String M_ID = "";
	private String M_PW = "";
	private String M_NAME = "";
	private String M_KIND = "";
	private String M_EMAIL1 = "";
	private String M_EMAIL2 = "";
	private int M_TEL1 = 0;
	private int M_TEL2 = 0;
	private int M_TEL3 = 0;
	private int M_BIRTH1 = 0;
	private int M_BIRTH2 = 0;
	private int M_BIRTH3 = 0;
	private String M_FBCHK = "";
	private String M_KTCHK = "";
	private String M_NCHK = "";
	private String M_GCHK = "";
	private String M_EMAIL_CHK = "";
	private String M_TEL_CHK = "";
	private String M_SERVICE_CHK = "";
	private int M_SERVICE_DATE1 = 0;
	private int M_SERVICE_DATE2 = 0;
	private int M_SERVICE_DATE3 = 0;
	private String M_STATE = "";
	
	public MemberDTO() {}
	
<<<<<<< HEAD
=======
	public MemberDTO(int nUM, String m_ID, String m_PW, String m_NAME, String m_KIND, String m_EMAIL1, String m_EMAIL2,
			int m_TEL1, int m_TEL2, int m_TEL3, int m_BIRTH1, int m_BIRTH2, int m_BIRTH3, String m_FBCHK,
			String m_KTCHK, String m_NCHK, String m_GCHK, String m_EMAIL_CHK, String m_TEL_CHK, String m_SERVICE_CHK,
			int m_SERVICE_DATE1, int m_SERVICE_DATE2, int m_SERVICE_DATE3, String m_STATE) {
		super();
		NUM = nUM;
		M_ID = m_ID;
		M_PW = m_PW;
		M_NAME = m_NAME;
		M_KIND = m_KIND;
		M_EMAIL1 = m_EMAIL1;
		M_EMAIL2 = m_EMAIL2;
		M_TEL1 = m_TEL1;
		M_TEL2 = m_TEL2;
		M_TEL3 = m_TEL3;
		M_BIRTH1 = m_BIRTH1;
		M_BIRTH2 = m_BIRTH2;
		M_BIRTH3 = m_BIRTH3;
		M_FBCHK = m_FBCHK;
		M_KTCHK = m_KTCHK;
		M_NCHK = m_NCHK;
		M_GCHK = m_GCHK;
		M_EMAIL_CHK = m_EMAIL_CHK;
		M_TEL_CHK = m_TEL_CHK;
		M_SERVICE_CHK = m_SERVICE_CHK;
		M_SERVICE_DATE1 = m_SERVICE_DATE1;
		M_SERVICE_DATE2 = m_SERVICE_DATE2;
		M_SERVICE_DATE3 = m_SERVICE_DATE3;
		M_STATE = m_STATE;
	}



>>>>>>> 1f258bbb1ccf1215572a53076409f307eb673dcf
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

	public String getM_PW() {
		return M_PW;
	}

	public void setM_PW(String m_PW) {
		M_PW = m_PW;
	}

	public String getM_NAME() {
		return M_NAME;
	}

	public void setM_NAME(String m_NAME) {
		M_NAME = m_NAME;
	}

	public String getM_KIND() {
		return M_KIND;
	}

	public void setM_KIND(String m_KIND) {
		M_KIND = m_KIND;
	}

	public String getM_EMAIL1() {
		return M_EMAIL1;
	}

	public void setM_EMAIL1(String m_EMAIL1) {
		M_EMAIL1 = m_EMAIL1;
	}

	public String getM_EMAIL2() {
		return M_EMAIL2;
	}

	public void setM_EMAIL2(String m_EMAIL2) {
		M_EMAIL2 = m_EMAIL2;
	}

	public int getM_TEL1() {
		return M_TEL1;
	}

	public void setM_TEL1(int m_TEL1) {
		M_TEL1 = m_TEL1;
	}

	public int getM_TEL2() {
		return M_TEL2;
	}

	public void setM_TEL2(int m_TEL2) {
		M_TEL2 = m_TEL2;
	}

	public int getM_TEL3() {
		return M_TEL3;
	}

	public void setM_TEL3(int m_TEL3) {
		M_TEL3 = m_TEL3;
	}

	public int getM_BIRTH1() {
		return M_BIRTH1;
	}

	public void setM_BIRTH1(int m_BIRTH1) {
		M_BIRTH1 = m_BIRTH1;
	}

	public int getM_BIRTH2() {
		return M_BIRTH2;
	}

	public void setM_BIRTH2(int m_BIRTH2) {
		M_BIRTH2 = m_BIRTH2;
	}

	public int getM_BIRTH3() {
		return M_BIRTH3;
	}

	public void setM_BIRTH3(int m_BIRTH3) {
		M_BIRTH3 = m_BIRTH3;
	}

	public String getM_FBCHK() {
		return M_FBCHK;
	}

	public void setM_FBCHK(String m_FBCHK) {
		M_FBCHK = m_FBCHK;
	}

	public String getM_KTCHK() {
		return M_KTCHK;
	}

	public void setM_KTCHK(String m_KTCHK) {
		M_KTCHK = m_KTCHK;
	}

	public String getM_NCHK() {
		return M_NCHK;
	}

	public void setM_NCHK(String m_NCHK) {
		M_NCHK = m_NCHK;
	}

	public String getM_GCHK() {
		return M_GCHK;
	}

	public void setM_GCHK(String m_GCHK) {
		M_GCHK = m_GCHK;
	}

	public String getM_EMAIL_CHK() {
		return M_EMAIL_CHK;
	}

	public void setM_EMAIL_CHK(String m_EMAIL_CHK) {
		M_EMAIL_CHK = m_EMAIL_CHK;
	}

	public String getM_TEL_CHK() {
		return M_TEL_CHK;
	}

	public void setM_TEL_CHK(String m_TEL_CHK) {
		M_TEL_CHK = m_TEL_CHK;
	}

	public String getM_SERVICE_CHK() {
		return M_SERVICE_CHK;
	}

	public void setM_SERVICE_CHK(String m_SERVICE_CHK) {
		M_SERVICE_CHK = m_SERVICE_CHK;
	}

	public int getM_SERVICE_DATE1() {
		return M_SERVICE_DATE1;
	}

	public void setM_SERVICE_DATE1(int m_SERVICE_DATE1) {
		M_SERVICE_DATE1 = m_SERVICE_DATE1;
	}

	public int getM_SERVICE_DATE2() {
		return M_SERVICE_DATE2;
	}

	public void setM_SERVICE_DATE2(int m_SERVICE_DATE2) {
		M_SERVICE_DATE2 = m_SERVICE_DATE2;
	}

	public int getM_SERVICE_DATE3() {
		return M_SERVICE_DATE3;
	}

	public void setM_SERVICE_DATE3(int m_SERVICE_DATE3) {
		M_SERVICE_DATE3 = m_SERVICE_DATE3;
	}

	public String getM_STATE() {
		return M_STATE;
	}

	public void setM_STATE(String m_STATE) {
		M_STATE = m_STATE;
	}
	
	
}

/*
CREATE TABLE `member` (
	`M_ID` VARCHAR(20) NOT NULL COLLATE 'utf8_general_ci',
	`M_PW` VARCHAR(20) NOT NULL COLLATE 'utf8_general_ci',
	`M_NAME` VARCHAR(20) NOT NULL COLLATE 'utf8_general_ci',
	`M_KIND` VARCHAR(20) NOT NULL COLLATE 'utf8_general_ci',
	`M_EMAIL1` VARCHAR(40) NOT NULL COLLATE 'utf8_general_ci',
	`M_EMAIL2` VARCHAR(40) NOT NULL COLLATE 'utf8_general_ci',
	`M_TEL1` INT(11) NOT NULL DEFAULT '0',
	`M_TEL2` INT(11) NOT NULL DEFAULT '0',
	`M_TEL3` INT(11) NOT NULL DEFAULT '0',
	`M_BIRTH1` INT(11) NOT NULL DEFAULT '0',
	`M_BIRTH2` INT(11) NOT NULL DEFAULT '0',
	`M_BIRTH3` INT(11) NOT NULL DEFAULT '0',
	`M_FBCHK` VARCHAR(20) NOT NULL DEFAULT 'N' COLLATE 'utf8_general_ci',
	`M_KTCHK` VARCHAR(20) NOT NULL DEFAULT 'N' COLLATE 'utf8_general_ci',
	`M_NCHK` VARCHAR(20) NOT NULL DEFAULT 'N' COLLATE 'utf8_general_ci',
	`M_GCHK` VARCHAR(20) NOT NULL DEFAULT 'N' COLLATE 'utf8_general_ci',
	`M_EMAIL_CHK` VARCHAR(20) NOT NULL DEFAULT 'N' COLLATE 'utf8_general_ci',
	`M_TEL_CHK` VARCHAR(20) NOT NULL DEFAULT 'N' COLLATE 'utf8_general_ci',
	`M_SERVICE_CHK` VARCHAR(20) NOT NULL DEFAULT 'N' COLLATE 'utf8_general_ci',
<<<<<<< HEAD
	`M_SERVICE_DATE` VARCHAR(50) NOT NULL DEFAULT 'N' COLLATE 'utf8_general_ci',
=======
	`M_SERVICE_DATE1` INT(11) NOT NULL DEFAULT '0',
	`M_SERVICE_DATE2` INT(11) NOT NULL DEFAULT '0',
	`M_SERVICE_DATE3` INT(11) NOT NULL DEFAULT '0',
>>>>>>> 1f258bbb1ccf1215572a53076409f307eb673dcf
	`M_STATE` VARCHAR(20) NOT NULL DEFAULT 'N' COLLATE 'utf8_general_ci',
	PRIMARY KEY (`M_ID`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
<<<<<<< HEAD
;


*/
=======
;*/
>>>>>>> 1f258bbb1ccf1215572a53076409f307eb673dcf
