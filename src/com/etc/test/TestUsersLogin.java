package com.etc.test;

import com.etc.entity.Users;
import com.etc.service.UsersSerivce;
import com.etc.service.impl.UsersSerivceImpl;

public class TestUsersLogin {

	public static void main(String[] args) {
		// TODO xxxxxxxxxx

		UsersSerivce us = new UsersSerivceImpl();

		Users u = us.login("ssss", "123123123");
		Users u1 = us.login("sasss", "12d3123123");
		Users u2 = us.login("ds", "123123f123");
		Users u3 = us.login("ssdss", "f");
		

		System.out.println(u);
	}

}
