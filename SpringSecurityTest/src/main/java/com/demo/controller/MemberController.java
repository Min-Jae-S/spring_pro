package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.MemberVO;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/member")
@Controller
public class MemberController {
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "member/loginForm";
	}

	@GetMapping("/joinForm")
	public String joinForm() {
		return "member/loginForm";
	}
	
	@PostMapping("/join")
	public String join(MemberVO memberVO) {
		return "redirect:/member/loginForm";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "error/access-denied";
	}
	
}
