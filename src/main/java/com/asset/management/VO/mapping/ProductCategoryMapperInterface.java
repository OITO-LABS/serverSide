
package com.asset.management.VO.mapping;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.asset.management.VO.ProductCategoryVO;
import com.asset.management.dao.entity.ProductCategoryEntity;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapperInterface {
	ProductCategoryMapperInterface INSTANCE = Mappers.getMapper(ProductCategoryMapperInterface.class);

	@Mapping(source = "productCategoryName", target = "productCategoryName")

	ProductCategoryEntity assetConvertion(ProductCategoryVO productCategoryVO);

	@InheritInverseConfiguration
	ProductCategoryVO assetReConvertion(ProductCategoryEntity productCategoryEntity);

	List<ProductCategoryVO> assetReConvertion(List<ProductCategoryEntity> productEntity);
}
