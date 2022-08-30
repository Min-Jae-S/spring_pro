package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Bean
//	public UserDetailsService userDatailsServiceImpl() {
//		return new UserDetailsServiceImpl();
//	}
//	
//	// UserDetailsService, PasswordEncoder 등록
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDatailsServiceImpl()).passwordEncoder(passwordEncoder());
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		CharacterEncodingFilter filter = new CharacterEncodingFilter();
//		filter.setEncoding("UTF-8");
//		filter.setForceEncoding(true);
//		http.addFilterBefore(filter, CsrfFilter.class);

		http.authorizeRequests()
				.antMatchers("/all").permitAll()
				.antMatchers("/member").access("hasRole('ROLE_MEMBER')")
				.antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
	}
	
	// 패스워드 인코딩 Bean 등록
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}
