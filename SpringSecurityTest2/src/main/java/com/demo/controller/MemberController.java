package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.domain.AuthVO;
import com.demo.domain.MemberVO;
import com.demo.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member")
@Controller
public class MemberController {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		log.info("====================== loginForm	======================");
		
		return "member/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		log.info("====================== joinForm	======================");
		
		return "member/joinForm";
	}
	
	@PostMapping("/join")
	public String join(MemberVO memberVO) {
		log.info("====================== join 	======================");
		log.info("memberVO : {}", memberVO);
		
		String rawPassword = memberVO.getMemberPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		memberVO.setMemberPassword(encPassword);
		
		int result = memberMapper.insertMember(memberVO);
		
		if(result == 1) {
			AuthVO authVO = new AuthVO();
			authVO.setMemberId(memberVO.getMemberId());
			authVO.setAuth(memberVO.getMemberRole());
			
			memberMapper.insertAuth(authVO);
		}
		
		return "redirect:/member/loginForm";
	}
	
	@GetMapping("/memberList")
	public String readMemberList(Model model) {
		log.info("====================== readMemberList	======================");
		
		List<MemberVO> list = memberMapper.readMemberList();
		log.info("list : {}", list);
		
		model.addAttribute("list", list);
		
		return "member/memberList";
	}

	@GetMapping("/memberInfo")
	public String getMemberInfo() {
		log.info("====================== getMemberInfo	======================");
		return "member/memberInfo";
	}
	
	@GetMapping("/updateForm")
	public String updateForm() {
		log.info("====================== updateForm	======================");
		return "member/updateForm";
	}

	@PostMapping("/update")
	public String update(MemberVO memberVO) {
		log.info("====================== update	======================");
		log.info("memberVO : {}", memberVO);
		
		return "redirect:/member/memberInfo";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "error/access-denied";
	}
	
}
