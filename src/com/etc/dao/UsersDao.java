package com.etc.dao;

import java.util.List;

import com.etc.entity.Users;
import com.etc.util.PageData;

public interface UsersDao {

	// ��ѯ����article
	public List<Users> queryUsers();

	public boolean addUsers(Users users);
	
	//�����û����������ѯ�û���Ϣ
	public Users queryUsersByNameAndPwd(String userName,String userPwd);
	
	public PageData<Users> queryByPage(int page,int pageSize,String userNameLike);
	
	public boolean queryByName(String userName);

}
