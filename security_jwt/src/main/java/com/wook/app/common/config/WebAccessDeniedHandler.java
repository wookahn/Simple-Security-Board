package com.wook.app.common.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class WebAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		int errorCode = HttpServletResponse.SC_FORBIDDEN;
		System.err.println("ERROR CODE: " + errorCode);
		
		response.sendError(errorCode, "접근 권한 없음");
		
	} // handle() end
	
} // WebAccessDeniedHandler end