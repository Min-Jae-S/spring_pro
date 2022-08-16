package kr.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.board.entity.Member;

@Mapper
public interface MemberMapper {
	
	public Member memCheckId(String memId);
	
	public int memRegister(Member member); // 성공:1, 실패:0
	
	public Member memLogin(Member member);
	
}
