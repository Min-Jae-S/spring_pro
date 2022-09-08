package com.demo.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	private final String DEFAULT_FAILURE_URL = "/member/loginForm";
	
	/*
	 * UsernameNotFoundException 	: 계정 없음
	 * BadCredentialsException 		: 비밀번호 불일치
	 * AccountExpiredException		: 계정만료
	 * CredentialsExpiredException	: 비밀번호 만료
	 * DisabledException			: 계정 비활성화
	 * LockedException				: 계정 잠김
	 * 
	 * InternalAuthenticationServiceException은 아이디가 존재하지 않을때 뿐만아니라
	 * 인증요청 대한 처리가 이루어질 때 발생하는 모든 시스템 에러에 대해 발생하는 예외입니다.
	 * 출처: https://to-dy.tistory.com/92 [todyDev:티스토리]
	 * 
	 */
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
//		Object object = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		
		String loginFailMsg = null;
		
		if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
			loginFailMsg = "아이디 또는 비밀번호를 확인해주세요.";
			
//		} else if (exception instanceof AuthenticationServiceException) {
//			loginFailMsg = "존재하지 않는 사용자입니다.";
			
		} else if(exception instanceof LockedException || exception instanceof DisabledException 
					|| exception instanceof AccountExpiredException) {
			loginFailMsg = "사용할 수 없는 계정입니다. 관리자에게 문의하세요.";

		} else if(exception instanceof CredentialsExpiredException) {
			loginFailMsg = "비밀번호의 유효기간이 만료되었습니다. 관리자에게 문의하세요.";
			
		} else {
			loginFailMsg = "오류가 발생하였습니다. 관리자에게 문의하세요.";
		}
		
		request.setAttribute("loginFailMsg", loginFailMsg);
		log.info("Login Fail, loginFailMsg : {}", loginFailMsg);

		RequestDispatcher dispatcher = request.getRequestDispatcher(DEFAULT_FAILURE_URL);
		dispatcher.forward(request, response);
	}

}
