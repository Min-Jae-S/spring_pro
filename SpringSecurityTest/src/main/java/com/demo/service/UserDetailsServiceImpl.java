package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.entity.CustomUserDetails;
import com.demo.entity.MemberVO;
import com.demo.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		
		log.info("## loadUserByUsername ##");
		log.info("memberId : " + memberId);
		
		MemberVO memberVO = memberMapper.checkLogin(memberId);
		log.info("memberVO : " + memberVO);
		
		if(memberVO != null) {
			return new CustomUserDetails(memberVO);
		} else {
			throw new UsernameNotFoundException("This id does not exist.");
		}
			
	}

}
