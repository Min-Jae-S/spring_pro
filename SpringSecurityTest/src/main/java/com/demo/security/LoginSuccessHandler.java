package com.demo.security;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
																	Authentication authentication) throws IOException {
		log.info("Login Success");
		clearAuthenticationAttributes(request);
		
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		log.info("authenticated memberVO : {}", customUserDetails.getMemberVO());
		
		String redirectUrl = decideRedirectUrl(request, response);
		log.info("redirectUrl : {}", redirectUrl);
		
		//RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		//redirectStrategy.sendRedirect(request, response, redirectUrl);
		response.sendRedirect(redirectUrl);
	}
	
	// 인증을 하지 않은 상태에서 권한이 필요한 화면에 접근하려 할 경우, Spring Security는 로그인 화면으로 redirect 한다.
	// 이 때, 로그인 화면을 띄우기 전 요청된 정보를 세션에 저장하게 된다. (이전 요청 URL 등)
	
 	// Redirect Url
 	// case1 : session에 저장된 Url | 인증을 하지 않은 상태에서 권한이 필요한 화면에 접근한 경우	  						
	// case2 : 이전 Url				| 일반 화면에서 로그인 링크를 통해 이동한 경우
 	// case3 : default Url 		   	| 사용자가 직접 로그인 화면으로 이동한 경우(즐겨찾기, 주소 등) 
	private String decideRedirectUrl(HttpServletRequest request, HttpServletResponse response) {
		String redirectUrl = request.getContextPath();

		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if(savedRequest != null) {
			redirectUrl = savedRequest.getRedirectUrl();
			requestCache.removeRequest(request, response);
		} else {
			String refererUrl = (String) request.getSession().getAttribute("refererUrl");
			
			if(refererUrl != null) {
				redirectUrl = refererUrl;
			}
		}
		
		return redirectUrl;
	}
	 
	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			return;
		}
		
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
