
package com.asset.management.VO.mapping;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.asset.management.VO.EmployeeVo;
import com.asset.management.dao.entity.Employee;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface EmployeeMapping {
	EmployeeMapping INSTANCE = Mappers.getMapper(EmployeeMapping.class);

	EmployeeVo employeeMap(Employee employee);

	@InheritInverseConfiguration
	Employee Map(EmployeeVo employeevo);

	List<EmployeeVo> employeeListConvert(List<Employee> emp);

	List<EmployeeVo> employeePageMap(Page<Employee> emp);

}
