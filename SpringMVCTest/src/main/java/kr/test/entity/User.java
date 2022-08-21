package kr.test.entity;

import lombok.Data;

@Data
public class User {
	
	private String UserId;
	private String UserPassword;
	private int UserAge;
	private boolean UserAgree;
	
	public User() {
		
	}
	
	public User(String userId, String userPassword, int userAge, boolean userAgree) {
		UserId = userId;
		UserPassword = userPassword;
		UserAge = userAge;
		UserAgree = userAgree;
	}

}
