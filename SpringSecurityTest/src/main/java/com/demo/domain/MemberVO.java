package com.demo.domain;

import lombok.Data;

@Data
public class MemberVO {
	
	private int memberIdx;
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberEmail;
	private String memberRole;
	
}
