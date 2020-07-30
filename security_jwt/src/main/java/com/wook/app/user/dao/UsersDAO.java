package com.wook.app.user.dao;

import java.util.List;

import com.wook.app.user.model.UserVO;

public interface UsersDAO {
	public UserVO selectUser(String username);
	public List<String> selectUserRoles(String username);
	public int insertUser(UserVO user);
	public int insertUserRole(int no);
} // UsersDAO end