package com.asset.management.dao;

import com.asset.management.VO.LoginVo;
import com.asset.management.VO.ResponseVO;
import com.asset.management.dao.entity.Employee;
public interface LoginDao {

	ResponseVO update(LoginVo loginVo) throws Exception;
	void delete();
	ResponseVO create(Employee employee);

	LoginVo login(LoginVo logVo) throws Exception;
	Employee findEmp(String mail) throws Exception;


	

}

