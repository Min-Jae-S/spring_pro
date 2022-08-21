package kr.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.test.entity.User;


@Controller
public class TestController {
	
	@RequestMapping("/testJackson")
	public String testJackson() {
		return "test/test_jackson";
	}

	@RequestMapping("/objectMapper")
	public @ResponseBody String objectMapper() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		User user1 = new User("ID1", "PW1", 30, true);
		
		// Java Obejct --> JSON
		String JSON = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user1);
		System.out.println("<< JSON >>");
		System.out.println(JSON);
		System.out.println();
		
		// JSON --> Java Object
		User user2 = objectMapper.readValue(JSON, User.class);
		System.out.println("<< Java Object >>");
		System.out.println(user2);
		System.out.println();
		
		return JSON;
	}
	
	@RequestMapping("/getData")
	@ResponseBody
	public void getData(@RequestBody User user) {
		System.out.println("getData : " + user);
		
		//ObjectMapper objectMapper = new ObjectMapper();
		//User user = objectMapper.readValue(data, User.class);
	}
	
	@RequestMapping("/sendData")
	@ResponseBody
	public User sendData() {
		return new User("아이디2", "패스워드2", 25, true);
	}
    
    
    
}
