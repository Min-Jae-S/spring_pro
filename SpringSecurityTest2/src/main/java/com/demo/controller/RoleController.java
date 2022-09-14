package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Controller
public class RoleController {
	
	@GetMapping("/all")
	public String all() {
		return "roles/all";
	}

	@GetMapping("/memberOnly")
	public String user() {
		return "roles/memberOnly";
	}
	
	@GetMapping("/managerOnly")
	public String manager() {
		return "roles/managerOnly";
	}
	
	@GetMapping("/adminOnly")
	public String admin() {
		return "roles/adminOnly";
	}
}
