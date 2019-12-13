package com.asset.management.dao;

import java.util.List;
import org.springframework.data.domain.Page;

import com.asset.management.VO.AssetVO;
import com.asset.management.VO.EmployeeVo;
import com.asset.management.VO.PaginationVO;
import com.asset.management.dao.entity.Employee;

public interface EmployeeDao {
	List<EmployeeVo> selectAll();

	Long register(EmployeeVo employee) throws Exception;

	EmployeeVo view(Long id);

	void delete(Long id);

	void update(Long id, EmployeeVo obj) throws Exception;

	void remove(String id) throws Exception;

	Page<Employee> page(PaginationVO pagination);

	List<AssetVO> getAsset(Long id);

	Page<Employee> searchEmployee(PaginationVO pagination);

	List<String> disable(Long login);
	}



