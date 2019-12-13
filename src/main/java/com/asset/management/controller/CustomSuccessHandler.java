package com.asset.management.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Map;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.asset.management.VO.LoginVo;
import com.asset.management.VO.UserVo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_OK);
		String employee = null;
		response.addHeader(employee,"empId");
		ObjectMapper obj = new ObjectMapper();
		UserVo userV=(UserVo)authentication.getPrincipal();
		//obj.setSerializationInclusion(Include.NON_NULL);
		//Long employeeId=userV.getEmployeeId();
		//String data = obj.writeValueAsString(Map.DEFAULT_KEY_COLUMN_NAME("employeeIds"));
		LoginVo emp=new LoginVo();
		emp.setEmployeeId(userV.getEmployeeId());
		emp.setRole(userV.getRole());
		request.setAttribute("empId", emp.getEmployeeId());
		request.getSession().setAttribute("empId",emp.getEmployeeId());
		String data=obj.writeValueAsString(emp);
		response.getWriter().write(data);
		response.setContentType("application/json");
	}

}
