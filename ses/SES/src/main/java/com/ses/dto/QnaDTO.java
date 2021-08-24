package com.ses.dto;

public class QnaDTO {
	private int NUM = 0;
	private int Q_NUM = 0;
	private String Q_TITLE = "";
	private String M_ID = "";
	private int Q_PWD = 0;
	private String Q_CONTENT = "";
    private int Q_YEAR = 0;
    private int Q_MONTH = 0;
    private int Q_DAY = 0;
	private String Q_REPLY = "";
	
	public QnaDTO() {}
	
	public QnaDTO(String q_TITLE, String m_ID, int q_PWD, String q_CONTENT, int q_YEAR, int q_MONTH,
			int q_DAY, String q_REPLY) {
		super();
		this.Q_TITLE = q_TITLE;
		this.M_ID = m_ID;
		this.Q_PWD = q_PWD;
		this.Q_CONTENT = q_CONTENT;
		this.Q_YEAR = q_YEAR;
		this.Q_MONTH = q_MONTH;
		this.Q_DAY = q_DAY;
		this.Q_REPLY = q_REPLY;
	}
	

	public int getNUM() {
		return NUM;
	}


	public void setNUM(int nUM) {
		NUM = nUM;
	}
	

	public int getQ_NUM() {
		return Q_NUM;
	}

	public void setQ_NUM(int q_NUM) {
		Q_NUM = q_NUM;
	}

	public String getQ_TITLE() {
		return Q_TITLE;
	}

	public void setQ_TITLE(String q_TITLE) {
		Q_TITLE = q_TITLE;
	}

	public String getM_ID() {
		return M_ID;
	}

	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}

	public int getQ_PWD() {
		return Q_PWD;
	}

	public void setQ_PWD(int q_PWD) {
		Q_PWD = q_PWD;
	}

	public String getQ_CONTENT() {
		return Q_CONTENT;
	}

	public void setQ_CONTENT(String q_CONTENT) {
		Q_CONTENT = q_CONTENT;
	}

	public int getQ_YEAR() {
		return Q_YEAR;
	}

	public void setQ_YEAR(int q_YEAR) {
		Q_YEAR = q_YEAR;
	}

	public int getQ_MONTH() {
		return Q_MONTH;
	}

	public void setQ_MONTH(int q_MONTH) {
		Q_MONTH = q_MONTH;
	}

	public int getQ_DAY() {
		return Q_DAY;
	}

	public void setQ_DAY(int q_DAY) {
		Q_DAY = q_DAY;
	}

	public String getQ_REPLY() {
		return Q_REPLY;
	}

	public void setQ_REPLY(String q_REPLY) {
		Q_REPLY = q_REPLY;
	}
}
/*
CREATE TABLE `qna` (
	`Q_NUM` INT(100) NOT NULL AUTO_INCREMENT,
	`Q_TITLE` VARCHAR(100) NULL DEFAULT NULL,
	`M_ID` VARCHAR(20) NULL DEFAULT NULL,
	`Q_PWD` INT(5) NULL DEFAULT NULL,
	`Q_CONTENT` MEDIUMTEXT NULL DEFAULT NULL,
	`Q_YEAR` INT(11) NULL DEFAULT NULL,
	`Q_MONTH` INT(11) NULL DEFAULT NULL,
	`Q_DAY` INT(11) NULL DEFAULT NULL,
	`Q_REPLY` MEDIUMTEXT NULL DEFAULT NULL,
	PRIMARY KEY (`Q_NUM`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=14
;
*/