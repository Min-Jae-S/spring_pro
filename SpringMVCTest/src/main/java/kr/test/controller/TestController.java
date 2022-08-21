package kr.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.test.entity.User;


@Controller
public class TestController {
	
	@RequestMapping("/")
	public String main() {
		return "index";
	}

	@RequestMapping("/testJackson")
	public String testJackson() throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		User user1 = new User("아이디1", "패스워드1", 30, true);
		
		// Java Obejct --> JSON
		String JSON = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user1);
		System.out.println("<< JSON >>");
		System.out.println(JSON);
		
		System.out.println();
		
		// JSON --> Java Object
		User user2 = objectMapper.readValue(JSON, User.class);
		System.out.println("<< Java Object >>");
		System.out.println("User : " + user2);
		
		return "test/test_jackson";
	}
	
	@RequestMapping("/getData")
	@ResponseBody
	public String getData(User user) {
		System.out.println("User : " + user);
		
		return "getData success";
	}
	
	@RequestMapping("/sendData")
	@ResponseBody
	public User sendData() {
		return new User("아이디2", "패스워드2", 25, true);
	}
    
    
    
}
