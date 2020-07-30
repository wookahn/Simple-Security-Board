package com.wook.app.user.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String username, password, name;
	private Timestamp regdate;
	private List<GrantedAuthority> authorities;
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}


} // UserVO end