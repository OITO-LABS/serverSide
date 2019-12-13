package com.asset.management.VO.mapping;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.asset.management.VO.AssetAssignVO;
import com.asset.management.dao.entity.AssetAssignEntity;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.asset.management.VO.AssetAssignVO;
import com.asset.management.dao.entity.AssetAssignEntity;

@Mapper(componentModel = "spring", uses = { EmployeeMapping.class,
		AssetMapperInterface.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface AssetAssignMapper {
	AssetAssignMapper INSTANCE = Mappers.getMapper(AssetAssignMapper.class);

	@Mapping(source = "assetAssign", target = "assetAssign")
	@Mapping(source = "employee", target = "employee")
	@Mapping(source = "issueDate", target = "issueDate")
	AssetAssignEntity assetAssignConvertion(AssetAssignVO assetAssignVO);

	@InheritInverseConfiguration
	AssetAssignVO assetAssignReConvertion(AssetAssignEntity assetAssignEntity);

	List<AssetAssignVO> assetAssignReConvertion(List<AssetAssignEntity> assetassign);

	List<AssetAssignVO> assetAssignReConvertion1(List<AssetAssignEntity> asset);
}