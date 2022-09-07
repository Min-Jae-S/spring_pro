package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.domain.AuthVO;
import com.demo.domain.MemberVO;

@Mapper
public interface MemberMapper {

	public int insertMember(MemberVO memberVO);
	
	public List<MemberVO> readMemberList();
	
	public MemberVO checkLogin(String memberId);
	
	public void insertAuth(AuthVO authVO);
}
