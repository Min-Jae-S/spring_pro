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

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
											Authentication authentication) throws IOException, ServletException {
		log.info("Login Success");
		
		//WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
		//log.info("IP : {}", web.getRemoteAddress());
		//log.info("Session ID : {}", web.getSessionId());
		//log.info("Id : {}", authentication.getName());
		//log.info("Password: {}", authentication.getCredentials());
		
		List<GrantedAuthority> list = (List<GrantedAuthority>) authentication.getAuthorities();
		
		for(GrantedAuthority auth : list) {
			log.info("Authority : {}", auth.getAuthority());
		}
		
		HttpSession session = request.getSession();
		
		Enumeration<String> attrNames = session.getAttributeNames();
		while (attrNames.hasMoreElements()) {
			log.info("attrNames : {}", attrNames.nextElement());
		}

		String uri = request.getContextPath();
		String prevPage = (String) session.getAttribute("prevPage");
		
		SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		//SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if(prevPage != null) {
			uri = prevPage;
			session.removeAttribute("prevPage");
		}
		
		// 강제 redirect된 로그인 화면 --> 로그인 성공 
		if(savedRequest != null) {
			log.info("SPRING_SECURITY_SAVED_REQUEST is NOT NULL");
			uri = savedRequest.getRedirectUrl();

			RequestCache requestCache = new HttpSessionRequestCache();
			requestCache.removeRequest(request, response);
		}
		
		log.info("RedirectURI : {}", uri);
		response.sendRedirect(uri);
	}

}
