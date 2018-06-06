package com.etc.entity;

/**
 * 自定义实体类
 * @author Administrator
 *
 */
public class Users {
	
	private int userId;
	private String userName;
	private String userPwd;
	private int userState;//用户状态 0禁用 1启用
	private int userLevel;//用户等级1 普通用户 2管理员
	
	public Users() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Users(String userName, String userPwd, int userState, int userLevel) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
		this.userState = userState;
		this.userLevel = userLevel;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", userState="
				+ userState + ", userLevel=" + userLevel + "]";
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userPwd
	 */
	public String getUserPwd() {
		return userPwd;
	}
	/**
	 * @param userPwd the userPwd to set
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	/**
	 * @return the userState
	 */
	public int getUserState() {
		return userState;
	}
	/**
	 * @param userState the userState to set
	 */
	public void setUserState(int userState) {
		this.userState = userState;
	}
	/**
	 * @return the userLevel
	 */
	public int getUserLevel() {
		return userLevel;
	}
	/**
	 * @param userLevel the userLevel to set
	 */
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

}
