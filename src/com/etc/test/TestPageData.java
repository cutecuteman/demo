package com.etc.test;

import java.util.List;

import com.etc.entity.Users;
import com.etc.service.UsersSerivce;
import com.etc.service.impl.UsersSerivceImpl;
import com.etc.util.DBUtil;
import com.etc.util.PageData;

public class TestPageData {
    public static void main(String[] args) {
        PageData<Users> pd=DBUtil.getPage("select * from users", 2, 2, Users.class);
        System.out.println("当前是第"+pd.getPage()+"页");
        System.out.println("总记录"+pd.getTotal()+"条");
        System.out.println("总页数为"+pd.getTotalPage());
        
       for (Users u : pd.getData()) {
         //  System.out.println(u);
        
    }
       UsersSerivce usersSerivce=new UsersSerivceImpl();
       PageData<Users> userByPage = usersSerivce.getUserByPage(1, 4,"1");
       /*for (PageData u : userByPage) {
           System.out.println(u);
        
    }*/
       //System.out.println(userByPage);
       List<Users> data = userByPage.getData();
       for (Users users : data) {
         System.out.println(users);
    }
    }
}
