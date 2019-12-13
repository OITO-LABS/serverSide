package com.asset.management.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asset.management.dao.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long>{

	Login findByUsername(String username);

	@Query(value="SELECT * FROM login_details WHERE employee_id=:employeeId",nativeQuery=true)
	Login findByEmployeeId(@Param("employeeId") Long employeeId);

	@Modifying
	@Query(value = "Update login_details obj set obj.username=:email where obj.employee_id=:empId", nativeQuery = true)
	void update(@Param("empId") Long empId,@Param("email") String email);

	void deleteByEmployeeId(Long empId);

}
