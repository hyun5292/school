package com.spring.hamsamo.dto;

import java.sql.Timestamp;

public class BoardDTO {
	private int bNum = 0;
	private String bId = "";
	private String mId = "";
	private String bTitle = "";
	private String bContent = "";
	private String bDate = null;
	private int bHit = 0;
	private int bGroup = 0;
	private int bStep = 0;
	private int bIndent = 0;
	
	public BoardDTO() {
		
	}
	
	public BoardDTO(String bId, String mId, String bTitle, String bContent, String bDate, int bHit, int bGroup, int bStep,
			int bIndent) {
		super();
		this.bId = bId;
		this.mId = mId;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bHit = bHit;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
	}
	
	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public String getmId() {
		return mId;
	}

	public void setbmId(String mName) {
		this.mId = mId;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getbDate() {
		return bDate;
	}

	public void setbDate(Timestamp bDate) {
		this.bDate = (bDate.getYear()+1900+"-"+bDate.getMonth()+"-"+bDate.getDate());
	}

	public int getbHit() {
		return bHit;
	}

	public void setbHit(int bHit) {
		this.bHit = bHit;
	}

	public int getbGroup() {
		return bGroup;
	}

	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}

	public int getbStep() {
		return bStep;
	}

	public void setbStep(int bStep) {
		this.bStep = bStep;
	}

	public int getbIndent() {
		return bIndent;
	}

	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}
	
}
/*
CREATE TABLE `mainboard` (
	`bId` INT(10) NOT NULL AUTO_INCREMENT,
	`bName` VARCHAR(20) NULL DEFAULT NULL,
	`bTitle` VARCHAR(100) NULL DEFAULT NULL,
	`bContent` VARCHAR(500) NULL DEFAULT NULL,
	`bDate` DATE NULL DEFAULT sysdate(),
	`bHit` INT(10) NULL DEFAULT 0,
	`bGroup` INT(10) NULL DEFAULT NULL,
	`bStep` INT(10) NULL DEFAULT NULL,
	`bIndent` INT(10) NULL DEFAULT NULL,
	PRIMARY KEY (`bId`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
;*/