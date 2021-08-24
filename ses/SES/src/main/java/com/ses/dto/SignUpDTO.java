package com.ses.dto;

public class SignUpDTO {
	private String S_KIND = "";
    private String S_LINK = "";
    
    public SignUpDTO() {}
	
    public SignUpDTO(String s_KIND, String s_LINK) {
		super();
		S_KIND = s_KIND;
		S_LINK = s_LINK;
	}

	public String getS_KIND() {
		return S_KIND;
	}

	public void setS_KIND(String s_KIND) {
		S_KIND = s_KIND;
	}

	public String getS_LINK() {
		return S_LINK;
	}

	public void setS_LINK(String s_LINK) {
		S_LINK = s_LINK;
	}
}
/*
CREATE TABLE `sign_up` (
	`SU_KIND` VARCHAR(40) NULL DEFAULT NULL,
	`SU_LINK` VARCHAR(200) NULL DEFAULT NULL
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
*/