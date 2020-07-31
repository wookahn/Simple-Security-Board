package com.wook.app.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wook.app.user.model.UserVO;

@Repository
public class UsersDAOImpl implements UsersDAO {
	
	@Autowired private SqlSession session;
	
	@Override
	public UserVO selectUser(String username) {
		return session.selectOne("userSqlMap.selectUser", username);
	} // selectUser() end	
	
	@Override
	public List<String> selectUserRoles(String username) {
		return session.selectList("userSqlMap.selectUserRoles", username);
	} // selectUserRoles() end
	
	@Override
	public int insertUser(UserVO user) {
		return session.insert("userSqlMap.insertUser", user);
	} // insertUser() end
	
	@Override
	public int insertUserRole(int no) {
		return session.insert("userSqlMap.insertUserRole", no);
	} // insertUserRole() end
	
} // UsersDAOImpl end