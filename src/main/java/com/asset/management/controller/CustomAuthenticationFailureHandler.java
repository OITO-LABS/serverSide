package com.asset.management.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.asset.management.VO.LoginVo;
import com.asset.management.VO.ResponseVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationFailureHandler 
implements AuthenticationFailureHandler {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void onAuthenticationFailure(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException exception) 
    throws IOException, ServletException {
	  response.setStatus(HttpServletResponse.SC_OK);
     // response.setStatus(HttpStatus.UNAUTHORIZED.value());
     // Map<String, Object> data = new HashMap<>();
      ResponseVO responseVo=new ResponseVO();
      responseVo.setErrorcode( exception.getMessage());
      responseVo.setStatus("failed");
//      LoginVo log=new LoginVo();
//      log.setResponse(responseVo);
//      data.put(
//        "timestamp", 
//        Calendar.getInstance().getTime());
//      data.put(
//        "response", 
//        responseVo);
      
//     // response.getOutputStream()
//        .println(objectMapper.writeValueAsString(responseVo));
      ObjectMapper obj = new ObjectMapper();
      String data=obj.writeValueAsString(responseVo);
		response.getWriter().write(data);
		response.setContentType("application/json");
  }


}