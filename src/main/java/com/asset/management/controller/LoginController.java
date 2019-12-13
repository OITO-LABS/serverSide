
package com.asset.management.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asset.management.VO.EmployeeVo;
import com.asset.management.VO.LoginVo;
import com.asset.management.VO.Mail;
import com.asset.management.VO.ProfileVo;
import com.asset.management.VO.ResponseVO;
import com.asset.management.dao.entity.Employee;
import com.asset.management.service.LoginService;

@RestController
@RequestMapping("api/login")
public class LoginController {
	@Autowired
	private LoginService loginService;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	EmployeeController employee;

	@Autowired
	HttpServletResponse response;
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AssetController.class);

	// login

	@PostMapping
	public LoginVo login(@ModelAttribute final LoginVo logVo) {
		final ResponseVO response=new ResponseVO();
		try {
			final LoginVo logVO=loginService.login(logVo);
			response.setStatus("success");
			logVO.setResponse(response);
			return logVO;
		} catch (final Exception e) {

			e.printStackTrace();
			response.setStatus("failed");
			response.setMessage("Username or password wrong");
			logVo.setResponse(response);
		}
		return logVo;
	}

	@PostMapping("reset")
	public ResponseVO resetPassword(@ModelAttribute final LoginVo loginVo) {
		final ResponseVO response=new ResponseVO();
		try {
			response.setStatus("success");
			return loginService.resetPassword(loginVo);
		} catch (final Exception e) {
			response.setStatus("failed");
			response.setMessage("invalid mail id");
			e.printStackTrace();

		}
		return response;
	}
	@PostMapping("/send-mail")
	public ResponseVO mail(@RequestBody final Mail obj) {
		final ResponseVO status = new ResponseVO();
		try {
			final String mail=obj.getTo();
			final Employee emp=loginService.findEmp(mail);
			obj.setToken(loginService.generatePasswordToken(emp.getEmpId()));
			loginService.sendmail(obj);
			status.setStatus("success");
		} catch (final Exception ex) {
			status.setStatus("Failed!");
			status.setMessage(ex.getMessage());
		}
		return status;
	}
	
	@GetMapping("/activesession")
	public ProfileVo getSession() {
		Long empId=(Long)request.getSession().getAttribute("empId");
		return employee.employeeget(response,empId);
	}  
	
	@GetMapping("/logout")
	public ResponseVO logout() {
		request.getSession().invalidate();
		ResponseVO response=new ResponseVO();
		response.setStatus("success");
		return response;
	}
}

