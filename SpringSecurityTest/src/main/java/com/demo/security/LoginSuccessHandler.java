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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	/*
		인증을 하지 않은 상태에서 권한이 필요한 화면에 접근하려 할 경우, 
		Spring Security는 로그인 화면으로 redirect 한다.
		이 때, 로그인 화면을 띄우기 전 요청된 정보를 세션에 저장하게 된다. (이전 화면의 URL 등)
	*/
	
	// redirect Url
	// case1 : session에 저장된 Url
	//			> Spring Security에 의해 로그인 화면으로 redirect된 경우
	//		   	> 인증을 하지 않은 상태에서 권한이 필요한 화면에 접근하려 할 경우
	// case2 : REFERER header의 Url
	//			> 사용자가 직접 로그인 화면으로 이동한 경우
	// case3 : default
	private String decideRedirectUrl(HttpServletRequest request, HttpServletResponse response) {
		String redirectUrl = "/";

		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		// savedRequest가 Null인 경우는 언제?
		log.info("savedRequest is null? {}", savedRequest == null ? true : false);
		
		if(savedRequest != null) {
			redirectUrl = savedRequest.getRedirectUrl();
			
		} else {
			String refererUrl = request.getHeader("REFERER");
			log.info("refererUrl = {}", refererUrl);
			
			if(refererUrl != null) {
				redirectUrl = refererUrl;
			}
		}
		
		return redirectUrl;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
											Authentication authentication) throws IOException, ServletException {
		log.info("Login Success");
		
		/*
		WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
		log.info("IP : {}", web.getRemoteAddress());
		log.info("Session ID : {}", web.getSessionId());
		log.info("Id : {}", authentication.getName());
		log.info("Password: {}", authentication.getCredentials());
		
		List<GrantedAuthority> list = (List<GrantedAuthority>) authentication.getAuthorities();
		
		for(GrantedAuthority auth : list) {
			log.info("Authority : {}", auth.getAuthority());
		}
		*/
		
		String redirectUrl = decideRedirectUrl(request, response);
		log.info("redirectUrl : {}", redirectUrl);
		
		response.sendRedirect(redirectUrl);
	}

}
