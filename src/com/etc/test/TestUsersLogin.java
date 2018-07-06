package com.etc.test;

import com.etc.entity.Users;
import com.etc.service.UsersSerivce;
import com.etc.service.impl.UsersSerivceImpl;

public class TestUsersLogin {

	public static void main(String[] args) {
		// TODO xxxxxxxxxx

		UsersSerivce us = new UsersSerivceImpl();

		Users u = us.login("ssss", "123123123");

		System.out.println(u);
	}

}
