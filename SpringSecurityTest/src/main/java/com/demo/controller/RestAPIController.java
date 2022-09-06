package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class RestAPIController {
	
	@GetMapping("/all")
	public String all() {
		log.info("ALL CAN ACCESS");
		return "all";
	}

	@GetMapping("/memberOnly")
	public String user() {
		log.info("USER ONLY");
		return "user";
	}
	
	@GetMapping("/managerOnly")
	public String manager() {
		log.info("MANAGER ONLY");
		return "manager";
	}
	
	@GetMapping("/adminOnly")
	public String admin() {
		log.info("ADMIN ONLY");
		return "admin";
	}
}
