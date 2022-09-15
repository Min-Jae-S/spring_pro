package com.demo.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.domain.MemberVO;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	// Composition
	private MemberVO memberVO;

	public CustomUserDetails(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new SimpleGrantedAuthority(memberVO.getMemberRole()));

		return collect;
	}

	@Override
	public String getPassword() {
		return memberVO.getMemberPassword();
	}

	@Override
	public String getUsername() {
		return memberVO.getMemberId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}
}
