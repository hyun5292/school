package com.spring.hamsamo.dto;

import java.sql.Timestamp;

public class MemberDTO {
	private String mId = "";
	private String mName = "";
	private String mPw = "";
	private String mBirth = null;
	private String mTel1 = "";
	private String mTel2 = "";
	private String mTel3 = "";
	
	public MemberDTO() {
		
	}

	public MemberDTO(String mId, String mName, String mPw, String mBirth, String mTel1, String mTel2, String mTel3) {
		super();
		this.mId = mId;
		this.mName = mName;
		this.mPw = mPw;
		this.mBirth = mBirth;
		this.mTel1 = mTel1;
		this.mTel2 = mTel2;
		this.mTel3 = mTel3;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmPw() {
		return mPw;
	}

	public void setmPw(String mPw) {
		this.mPw = mPw;
	}

	public String getmBirth() {
		return mBirth;
	}

	public void setmBirth(String mBirth) {
		this.mBirth = mBirth;
	}

	public String getmTel1() {
		return mTel1;
	}

	public void setmTel1(String mTel1) {
		this.mTel1 = mTel1;
	}

	public String getmTel2() {
		return mTel2;
	}

	public void setmTel2(String mTel2) {
		this.mTel2 = mTel2;
	}

	public String getmTel3() {
		return mTel3;
	}

	public void setmTel3(String mTel3) {
		this.mTel3 = mTel3;
	}
	
	
}
