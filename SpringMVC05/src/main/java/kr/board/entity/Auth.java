package kr.board.entity;

import lombok.Data;

/*
	authIdx     number 	constraint AUTH_PK primary key,
	memId 	    varchar2(50) 	not null constraint AUTH_FK references member_tbl(memId),
	auth        varchar2(50) 	not null
*/

@Data
public class Auth {
	
	private int authIdx;
	private String memId;	// 회원 아이디
	private String auth;	// 회원 권한(ROLE_USER, ROLE_MANAGER, ROLE_ADMIN)
	
}
