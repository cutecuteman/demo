package com.etc.test;

import java.util.List;

import com.etc.dao.UsersDao;
import com.etc.dao.impl.UsersDaoImpl;
import com.etc.entity.Users;

public class TestUsersDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UsersDao ud = new UsersDaoImpl();

		List<Users> list = ud.queryUsers();

		for (Users users : list) {
			System.out.println(users);
		}
	}

}
