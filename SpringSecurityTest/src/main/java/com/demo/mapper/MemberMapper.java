package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.entity.MemberVO;

@Mapper
public interface MemberMapper {

	public void insertMember(MemberVO memberVO);
	
	public List<MemberVO> getMemberList();
}
