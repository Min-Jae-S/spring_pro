package com.demo.domain;

import lombok.Data;

@Data
public class AuthVO {
	
	private int authIdx;
	private String memberId;
	private String auth;
}
