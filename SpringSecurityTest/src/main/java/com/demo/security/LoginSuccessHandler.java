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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
											Authentication authentication) throws IOException, ServletException {
		log.info("Login Success");
		
		WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
		log.info("IP : {}", web.getRemoteAddress());
		log.info("Session ID : {}", web.getSessionId());
		log.info("ID : {}", authentication.getName());
		
		List<GrantedAuthority> list = (List<GrantedAuthority>) authentication.getAuthorities();
		for(GrantedAuthority auth : list) {
			log.info("Authority : {}", auth.getAuthority());
		}
		
		RequestCache requestCache = new HttpSessionRequestCache();
		HttpSession session = request.getSession();
		SavedRequest savedRequest =(SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		//SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		Enumeration<String> attrNames = session.getAttributeNames();
		while (attrNames.hasMoreElements()) {
			log.info("attrNames : {}", attrNames.nextElement());
		}
				
		if(savedRequest != null) {
			String uri = savedRequest.getRedirectUrl();
			log.info("uri : {}", uri);
			
			requestCache.removeRequest(request, response);
			response.sendRedirect(uri);
		}
		
	}

}
