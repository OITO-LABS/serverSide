
package com.asset.management.VO.mapping;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.asset.management.VO.AssetVO;
import com.asset.management.dao.entity.AssetEntity;

@Mapper(componentModel = "spring", uses = { ProductCategoryMapperInterface.class })
public interface AssetMapperInterface {
	AssetMapperInterface INSTANCE = Mappers.getMapper(AssetMapperInterface.class);

	AssetEntity assetConvertion(AssetVO assetVO);

	@InheritInverseConfiguration
	List<AssetVO> assetReConvertion(List<AssetEntity> assetdetails);
	AssetVO assetReConvertion(AssetEntity assetEntity);

}