package com.etc.test;

import com.etc.entity.Users;
import com.etc.service.UsersSerivce;
import com.etc.service.impl.UsersSerivceImpl;

public class TestUsersLogin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UsersSerivce us = new UsersSerivceImpl();

		Users u = us.login("affff", "123456");

		System.out.println(u);
	}

}
