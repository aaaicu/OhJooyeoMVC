package kr.co.ohjooyeo.vo;

public class UserVO {
	private String userId;
	private String password;

	public UserVO(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", password=" + password + "]";
	}

}
