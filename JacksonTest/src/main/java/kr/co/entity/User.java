package kr.co.entity;

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

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	public int getUserAge() {
		return UserAge;
	}

	public void setUserAge(int userAge) {
		UserAge = userAge;
	}

	public boolean isUserAgree() {
		return UserAgree;
	}

	public void setUserAgree(boolean userAgree) {
		UserAgree = userAgree;
	}

	@Override
	public String toString() {
		return "User [UserId=" + UserId + ", UserPassword=" + UserPassword + ", UserAge=" + UserAge + ", UserAgree="
				+ UserAgree + "]";
	}
}
