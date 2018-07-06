package com.etc.test;

import com.etc.entity.Users;
import com.etc.service.UsersSerivce;
import com.etc.service.impl.UsersSerivceImpl;

public class TestUsersLogin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UsersSerivce us = new UsersSerivceImpl();

<<<<<<< HEAD
		Users u = us.login("ssss", "123123123");
		Users u1 = us.login("sasss", "12d3123123");
		Users u2 = us.login("ds", "123123f123");
		Users u3 = us.login("ssdss", "f");
		
=======
		Users u = us.login("affff", "123456");
>>>>>>> parent of 5ceb359... test

		System.out.println(u);
	}

}
