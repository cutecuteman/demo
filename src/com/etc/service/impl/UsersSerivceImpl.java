package com.etc.service.impl;

import java.util.List;

import com.etc.dao.UsersDao;
import com.etc.dao.impl.UsersDaoImpl;
import com.etc.entity.Users;
import com.etc.service.UsersSerivce;
import com.etc.util.PageData;

public class UsersSerivceImpl implements UsersSerivce {

	UsersDao ud = new UsersDaoImpl();

	public List<Users> getUsers() {
		return ud.queryUsers();
	}

	public boolean addUsers(Users users) {
		return ud.addUsers(users);
	}

	@Override
	public Users login(String userName, String userPwd) {
		// TODO Auto-generated method stub
		return ud.queryUsersByNameAndPwd(userName, userPwd);
	}

    @Override
    public PageData<Users> getUserByPage(int page, int pageSize,String userNameLike) {
        // TODO Auto-generated method stub
        return ud.queryByPage(page, pageSize,userNameLike);
    }

    @Override
    public boolean getUserByName(String userName) {
        // TODO Auto-generated method stub
        return ud.queryByName(userName);
    }
	
}
