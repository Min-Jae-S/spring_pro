package kr.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.board.entity.Member;
import kr.board.entity.MemberUser;
import kr.board.mapper.MemberMapper;

// 2) UserDetailService
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Member 타입의 인스턴스(DB에서 가져온 회원 정보)를 UserDetails로 형변환 해야 한다. Why? 스프링 시큐리티의 필터를 사용하기 위해
		// 1. Member 클래스에 UserDetails 인터페이스를 구현
		// 2. UserDetails 인터페이스를 구현한 클래스(User)를 Member 클래스가 상속
		// 3. UserDetails 인터페이스를 구현한 클래스(User)를 상속받는 별도의 클래스를 생성(MemberUser)
		//	--> Member를 포함하는 클래스
		//	--> Member의 모든 정보를 추가적으로 사용할 수 있는 장점

		Member DBmember = memberMapper.getMember(username); // username = memId
		
		if(DBmember != null) {
			// 최종적으로 Spring Security Context에 객체 바인딩 
			return new MemberUser(DBmember);
		} else {
			throw new UsernameNotFoundException("this user (" + username + ") does not exist.");
		}
	}

}
