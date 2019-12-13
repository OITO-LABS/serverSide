package com.asset.management.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.asset.management.VO.ResponseVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_OK);
        HttpSession session = request.getSession();
        if (session != null){
            session.removeAttribute("empId");
        }

		ResponseVO responseVo=new ResponseVO();
	    responseVo.setStatus("success");
	    ObjectMapper obj = new ObjectMapper();
	    String data=obj.writeValueAsString(responseVo);
	    response.getWriter().write(data);
		response.setContentType("application/json");
	}

}
