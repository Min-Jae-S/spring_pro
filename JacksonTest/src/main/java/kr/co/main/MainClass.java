package kr.co.main;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.entity.User;

public class MainClass {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ObjectMapper mapper = new ObjectMapper();
		User user1 = new User("아이디1", "패스워드", 30, true);
		
        // Java Obejct --> JSON
		String JSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user1);
		System.out.println(JSON);
        
        // JSON --> Java Object
		User user2 = mapper.readValue(JSON, User.class);
		System.out.println();
		System.out.println(user2);
	}

}
