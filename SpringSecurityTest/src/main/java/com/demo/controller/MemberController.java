package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entity.MemberVO;
import com.demo.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member")
@Controller
public class MemberController {
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "member/loginForm";
	}

	@GetMapping("/joinForm")
	public String joinForm() {
		return "member/joinForm";
	}
	
	@PostMapping("/join")
	public String join(MemberVO memberVO) {
		log.info("## join ##");
		log.info("memberVO : " + memberVO);
		
		String rawPassword = memberVO.getMemberPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		memberVO.setMemberPassword(encPassword);
		
		memberMapper.insertMember(memberVO);
		
		return "redirect:/member/loginForm";
	}
	
	@GetMapping("/memberList")
	public String getMemberList(Model model) {
		log.info("## getMemberList ##");
		
		List<MemberVO> list = memberMapper.getMemberList();
		log.info("list : " + list);
		
		model.addAttribute("list", list);
		
		return "member/memberList";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "error/access-denied";
	}
	
}
