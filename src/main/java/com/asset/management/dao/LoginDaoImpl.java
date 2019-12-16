package com.asset.management.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.asset.management.VO.LoginVo;
import com.asset.management.VO.ResponseVO;
import com.asset.management.VO.mapping.LoginMapper;
import com.asset.management.dao.entity.Employee;
import com.asset.management.dao.entity.Login;
import com.asset.management.dao.entity.Role;
import com.asset.management.dao.entity.Status;
import com.asset.management.dao.repository.EmployeeRepository;
import com.asset.management.dao.repository.LoginRepository;
import com.asset.management.service.LoginService;

import ch.qos.logback.classic.Logger;

@Component
public class LoginDaoImpl implements LoginDao {
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private LoginMapper loginMap;
	
	@Autowired
	LoginService logService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	@Override
	public ResponseVO create(Employee employee) {
		Login log=new Login();
		ResponseVO response=new ResponseVO();
		log.setUsername(employee.getEmail());
		log.setStatus(Status.Inactive);
		log.setRole(Role.employee);
		log.setEmployeeId(employee.getEmpId());
		//log.setPassword(passwordEncoder.encode("Hello@Oito"));
		if(loginRepository.saveAndFlush(log).getLoginId()!=null) {
			response.setMessage("Login details added");
			response.setStatus("success");
		}
		return response;
	}

	@Override
	public LoginVo login(LoginVo logVo) throws Exception{
		Login log=loginRepository.findByUsername(logVo.getUsername());
		if(log!=null && log.getPassword().equals(logVo.getPassword())) {
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Calendar calobj = Calendar.getInstance();
			log.setLoginTime(calobj.getTime());
			loginRepository.save(log);
			return loginMap.loginReConvertion(log);
		}
		else {
			throw new Exception("Invalid username or password");
		}
		
	}


	@Override
	public void delete() {


	}

	public ResponseVO update(LoginVo logVo) throws Exception {
		ResponseVO response=new ResponseVO();
		Login logEntity=loginMap.loginConvertion(logVo);
		//Login demoEntity=loginRepository.findByEmployeeId(logVo.getEmployeeId());
		Login demoEntity=loginRepository.findByUsername(logVo.getUsername());
		//if(demoEntity.getUsername().equals(logEntity.getUsername())) {
		if(demoEntity!=null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(logEntity.getPassword());
			demoEntity.setPassword(hashedPassword);
			demoEntity.setStatus(Status.Active);
			loginRepository.save(demoEntity);
			response.setStatus("success");
			
			response.setMessage("Password reset successfully");
		}
		else {
			throw new Exception("Not a valid user");
		}
		return response;
	}


	@Override
	public Employee findEmp(String mail) throws Exception {
		Employee emp = employeeRepository.findByEmail(mail);
		if(emp!=null) {
		return emp;
		}else {
			throw new Exception("Enter your registered e-mail address");
		}
	}
}
