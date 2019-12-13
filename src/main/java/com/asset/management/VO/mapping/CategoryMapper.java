package com.asset.management.VO.mapping;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.asset.management.dao.entity.CategoryDetails;
import com.asset.management.VO.CategoryVo;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

	@Mappings({ @Mapping(source = "categoryName", target = "categoryName") })

	CategoryDetails voConversion(CategoryVo obj);

	@InheritInverseConfiguration
	CategoryVo entityConversion(CategoryDetails obj);

	List<CategoryDetails> voListConvert(List<CategoryVo> obj);

	@InheritInverseConfiguration
	List<CategoryVo> entityListConvert(List<CategoryDetails> obj);

}
