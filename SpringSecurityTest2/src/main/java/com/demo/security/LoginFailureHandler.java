package com.demo.security;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
	
	@Autowired
	MessageSource messageSource;
	
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
	 * 출처: https://to-dy.tistory.com/92 
	 * 
	 */
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.info("Login Fail");
		
		String exceptionMessage = null;
		
		if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
			exceptionMessage = messageSource.getMessage("error.BadCredentials", null, Locale.getDefault());
			
//		} else if (exception instanceof AuthenticationServiceException) {
//			exceptionMessage = "존재하지 않는 사용자입니다.";
			
		} else if(exception instanceof LockedException || exception instanceof DisabledException || 
						exception instanceof AccountExpiredException) {
			exceptionMessage = messageSource.getMessage("errer.LockedDisabledAccountExpired", null, Locale.getDefault());
			
		} else if(exception instanceof CredentialsExpiredException) {
			exceptionMessage = messageSource.getMessage("error.CredentialsExpired", null, Locale.getDefault());
			
		} else {
			exceptionMessage = messageSource.getMessage("error.Etc", null, Locale.getDefault());
		}
		
		request.setAttribute("exceptionMessage", exceptionMessage);
		log.info("exceptionMessage : {}", exceptionMessage);
		log.info("message : {}", exception.getMessage());
		
		request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
	}

}
