package com.asset.management.VO.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.asset.management.VO.LoginVo;
import com.asset.management.dao.entity.Login;

@Mapper(componentModel = "spring", uses = { EmployeeMapping.class,
		AssetMapperInterface.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)

public interface LoginMapper {
	LoginMapper INSTANCE = Mappers.getMapper(LoginMapper.class);
	
	Login loginConvertion(LoginVo loginVo);
	@InheritInverseConfiguration
	LoginVo loginReConvertion(Login login);
}
