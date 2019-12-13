
package com.asset.management.VO.mapping;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.asset.management.VO.ReimbursementTrackVo;
import com.asset.management.dao.entity.ReimbursementTrack;

@Mapper(componentModel = "spring", uses = { ReimbursementMapper.class })
public interface ReimbursementTrackMapper {

	ReimbursementTrackMapper IreimbursementTrackerMapper = Mappers.getMapper(ReimbursementTrackMapper.class);

	@Mapping(source = "reimbursementId", target = "reimbursementId")
	@Mapping(source = "empNo", target = "empNo")
	@Mapping(source = "reimbursementDate", target = "reimbursementDate")
	@Mapping(source = "totalCost", target = "totalCost")
	@Mapping(source = "reimbursementDetails", target = "reimbursementDetails")

	ReimbursementTrack voConversion(ReimbursementTrackVo obj);

	@InheritInverseConfiguration
	ReimbursementTrackVo entityConversion(ReimbursementTrack obj);

	List<ReimbursementTrack> voListConvert(List<ReimbursementTrackVo> obj);

	@InheritInverseConfiguration
	List<ReimbursementTrackVo> entityListConvert(List<ReimbursementTrack> obj);

	List<ReimbursementTrackVo> entityPageListConvert(Page<ReimbursementTrack> obj);

}

