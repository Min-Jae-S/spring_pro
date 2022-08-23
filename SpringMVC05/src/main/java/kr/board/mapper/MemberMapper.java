package kr.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.board.entity.Auth;
import kr.board.entity.Member;

@Mapper
public interface MemberMapper {
	
	public String memCheckId(String memId);
	
	public int memRegister(Member member); // 성공:1, 실패:0
	
	public Member memLogin(Member member);
	
	public int memUpdate(Member member);
	
	public Member getMember(String memId);
	
	public void memProfileUpdate(@Param("memId") String memId, @Param("memProfile") String memProfile);
	
	public void insertAuth(Auth auth);
	
}
