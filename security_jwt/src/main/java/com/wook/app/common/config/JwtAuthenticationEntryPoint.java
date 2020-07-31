package com.wook.app.common.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		int errorCode = HttpServletResponse.SC_UNAUTHORIZED;
		System.err.println("ERROR CODE: " + errorCode);
		
		response.sendError(errorCode, "인증되지 않은 사용자");
		
	} // commence() end
	
} // JwtAuthenticationEntryPoint end