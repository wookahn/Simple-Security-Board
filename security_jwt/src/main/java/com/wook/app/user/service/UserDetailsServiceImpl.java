package com.wook.app.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wook.app.user.dao.UsersDAO;
import com.wook.app.user.model.UserVO;
import com.wook.app.common.util.JwtUtil;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired private UsersDAO usersDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserVO user = usersDAO.selectUser(username);
		
		List<String> grantedList = usersDAO.selectUserRoles(username);
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		
		for (String role : grantedList) {
			roles.add(new SimpleGrantedAuthority(JwtUtil.TOKEN_PREFIX + role));
		} // for end
		
		user.setAuthorities(roles);
		
		System.err.println("USER: " + user);
		
		return user;

	} // loadUserByUsername() end
	
	public void register(UserVO user) {
		usersDAO.insertUser(user);
		usersDAO.insertUserRole(user.getNo());
	} // register() end
	
} // UserDetailsServiceImpl end