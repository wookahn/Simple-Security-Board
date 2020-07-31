package com.wook.app.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.wook.app.user.model.AuthenticationResponse;
import com.wook.app.user.model.UserVO;
import com.wook.app.user.service.UserDetailsServiceImpl;
import com.wook.app.common.util.JwtUtil;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private JwtUtil jwtUtil;
	@Autowired private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String index() {
		return "index";
	} // index() end
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserVO user) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		} catch (Exception e) {
			throw new Exception("Incorrect username or password\nError: ", e);
		} // try~catch end

		final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user.getUsername());
		final String jwt = jwtUtil.createToken(userDetails);
		
		System.err.println("------------------------------------------");
		System.err.println("jwt: " + jwt);
		System.err.println("------------------------------------------");

		System.err.println("getUsername: " + jwtUtil.getUsername(jwt));
		System.err.println("getExpiration: " + jwtUtil.getExpiration(jwt));
		System.err.println("Is Token valied? " + jwtUtil.validateToken(jwt, userDetails));
		System.err.println("------------------------------------------");

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	} // createAuthenticationToken() end
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public UserVO register(@RequestBody UserVO user) {
		
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		
		userDetailsServiceImpl.register(user);
		
		return user;
		
	} // register() end
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Authentication test() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication;
		
	} // test() end
	
} // UserController end