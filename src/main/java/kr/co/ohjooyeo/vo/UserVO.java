package kr.co.ohjooyeo.vo;

public class UserVO {
	private String userId;
	private String password;
	private String churchId;
	private String userCd;
	public UserVO(String userId, String password, String churchId, String userCd) {
		super();
		this.userId = userId;
		this.password = password;
		this.churchId = churchId;
		this.userCd = userCd;
	}
	public UserVO() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getChurchId() {
		return churchId;
	}
	public void setChurchId(String churchId) {
		this.churchId = churchId;
	}
	public String getUserCd() {
		return userCd;
	}
	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}
	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", password=" + password + ", churchId=" + churchId + ", userCd=" + userCd
				+ "]";
	}
	
	
}
