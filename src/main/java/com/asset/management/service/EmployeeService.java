
package com.asset.management.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.asset.management.VO.AssetVO;
import com.asset.management.VO.EmployeeVo;
import com.asset.management.VO.PaginationVO;
import com.asset.management.dao.entity.Employee;

public interface EmployeeService {

	List<EmployeeVo> selectAll();

	Long register(EmployeeVo employee) throws Exception;

	void delete(Long id);

	EmployeeVo view(Long id);

	void update(Long id, EmployeeVo obj) throws Exception;

	Page<Employee> page(PaginationVO pagination);

	List<AssetVO> getAsset(Long id);

	Page<Employee> searchEmployee(PaginationVO pagination);

	void remove(String id) throws Exception;

	List<String> disable(Long login);

	void upload(MultipartFile image, Long id) throws Exception;

	void viewImage(HttpServletResponse response, Long empId) throws Exception;
	
	


}