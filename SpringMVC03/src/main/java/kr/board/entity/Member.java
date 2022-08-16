package kr.board.entity;

import lombok.Data;

//memIdx 			number constraint MEM_TBL_PK primary key,
//memId 			varchar2(20) 	not null,
//memPassword 		varchar2(20) 	not null,
//memName 			varchar2(20) 	not null,
//memAge			number, 
//memGender 		varchar2(20),
//memEmail			varchar2(50),
//memProfile		varchar2(50)

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
	
}
