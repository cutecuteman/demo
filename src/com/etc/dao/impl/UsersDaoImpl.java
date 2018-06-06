package com.etc.dao.impl;

import java.util.List;

import com.etc.dao.UsersDao;
import com.etc.entity.Users;
import com.etc.util.DBUtil;
import com.etc.util.PageData;

public class UsersDaoImpl implements UsersDao {

	// ��ѯ����Users
	@SuppressWarnings("unchecked")
	public List<Users> queryUsers() {
		return (List<Users>) DBUtil.select("select * from users", Users.class);

	}

	@Override
	public boolean addUsers(Users users) {
		// TODO Auto-generated method stub
		return DBUtil.execute("insert into users values(null,?,?,?,?)", users.getUserName(), users.getUserPwd(),
				users.getUserState(), users.getUserLevel()) > 0;
	}

	/**
	 * �����û����������ѯ�û���Ϣ
	 */
	public Users queryUsersByNameAndPwd(String userName, String userPwd) {
		// TODO Auto-generated method stub
		List<Users> list = (List<Users>) DBUtil.select("select * from users where userName=? and userPwd=?",
				Users.class, userName, userPwd);

		if (list.size() > 0)
			return list.get(0);
		return null;
	}

    @Override
    public PageData<Users> queryByPage(int page, int pageSize,String userNameLike) {
        // TODO Auto-generated method stub
        PageData<Users> pd = DBUtil.getPage("select * from users where userName like ?", page, pageSize, Users.class,"%"+userNameLike+"%");
        return pd;
    }

    @Override
    public boolean queryByName(String userName) {
        // TODO Auto-generated method stub
         Object obj = DBUtil.getFirst("select count(1) from users where userName=?", userName);
        // Integer integer=(Integer)obj;
         Long integer=(Long)obj;
         return integer>0;
    }

}
