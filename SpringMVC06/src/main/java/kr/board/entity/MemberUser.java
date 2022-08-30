package kr.board.entity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

// 3) 인증 후 사용자 정보를 저장하기(UserDetails)

@Data
public class MemberUser extends User {
	
	private Member member;

	public MemberUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}

	public MemberUser(Member member) { 
		// List<Auth> --> Collection<SimpleGrantedAuthority> // Collection<? extends GrantedAuthority> 
		// stream()를 통해 바이트열(binary)로 변환
		// SimpleGrantedAuthority --> 권한정보를 문자열로 저장
		super(member.getMemId(), member.getMemPassword(), 
			  member.getAuthList()
					.stream()
					.map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
					.collect(Collectors.toList()));
		this.member = member;
	}
	

}
