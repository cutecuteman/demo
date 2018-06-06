package com.etc.service;

import java.util.List;

import com.etc.entity.Users;
import com.etc.util.PageData;

public interface UsersSerivce {

	public List<Users> getUsers();

	public boolean addUsers(Users users);

	// �û���¼
	public Users login(String userName, String userPwd);
	
	public PageData<Users> getUserByPage(int page,int pageSize,String userNameLike);
	
	
	public boolean getUserByName(String userName);

}
