package kr.board.entity;

import java.util.List;

import lombok.Data;

/*
	memIdx 			number, 
	memId 			varchar2(20) not null constraint MEMBER_PK primary key,
	memPassword 	varchar2(70) not null,
	memName 		varchar2(20) not null,
	memAge			number, 
	memGender 		varchar2(20),
	memEmail		varchar2(50),
	memProfile		varchar2(50)
*/

@Data
public class Member {
	
	private int memIdx;
	private String memId;
	private String memPassword;
	private String memName;
	private int memAge;
	private String memGender;
	private String memEmail;
	private String memProfile;
	private List<Auth> authList;
	
}
