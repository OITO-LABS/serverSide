
package com.asset.management.VO.mapping;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.asset.management.VO.ReimbursementVo;
import com.asset.management.dao.entity.ReimbursementDetails;

@Mapper(componentModel = "spring", uses = { ReimbursementTrackMapper.class })
public interface ReimbursementMapper {
	ReimbursementMapper reimbursementMapper = Mappers.getMapper(ReimbursementMapper.class);

	@Mapping(source = "billDate", target = "billDate")
	@Mapping(source = "reimbursementDescription", target = "reimbursementDescription")
	@Mapping(source = "categoryName", target = "categoryName")
	@Mapping(source = "billNo", target = "billNo")
	@Mapping(source = "cost", target = "cost")

	ReimbursementDetails voConversion(ReimbursementVo obj);

	@InheritInverseConfiguration
	ReimbursementVo entityConversion(ReimbursementDetails obj);

	List<ReimbursementDetails> voListConvert(List<ReimbursementVo> obj);

	@InheritInverseConfiguration
	List<ReimbursementVo> entityListConvert(List<ReimbursementDetails> obj);

}

