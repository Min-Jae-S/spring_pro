create table myboard (
	idx 	    number constraint MYBOARD_PK primary key,
	title 	    varchar2(100) 	not null,
	content     varchar2(2000) 	not null,
	writer 	    varchar2(30) 	not null,
    writerId    varchar2(20)    not null,
	indate 	    date default sysdate,
	count 	    number default 0
);

create sequence myboard_seq
start with 1
increment by 1
minvalue 1;

/*
insert into myboard (idx, title, content, writer)
values(myboard_seq.nextval, '게시판 제목1', '게시판 내용1', '작성자1');
insert into myboard (idx, title, content, writer)
values(myboard_seq.nextval, '게시판 제목2', '게시판 내용2', '작성자2');
insert into myboard (idx, title, content, writer)
values(myboard_seq.nextval, '게시판 제목3', '게시판 내용3', '작성자3');
insert into myboard (idx, title, content, writer)
values(myboard_seq.nextval, '게시판 제목4', '게시판 내용4', '작성자4');
*/

select * from myboard order by idx desc;

drop table myboard cascade constraints;
drop sequence myboard_seq;


------------------------------------------------------------------------
create table mem_tbl (
	memIdx 			number constraint MEM_TBL_PK primary key,
	memId 			varchar2(20) 	not null,
	memPassword 	varchar2(20) 	not null,
	memName 		varchar2(20) 	not null,
	memAge			number, 
	memGender 		varchar2(20),
	memEmail		varchar2(50),
	memProfile		varchar2(50)
);

create sequence mem_tbl_seq
start with 1
increment by 1
minvalue 1;


