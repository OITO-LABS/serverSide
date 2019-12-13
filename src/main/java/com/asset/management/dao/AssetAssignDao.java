
package com.asset.management.dao;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.asset.management.VO.AssetAssignVO;
import com.asset.management.VO.ListPageData;
import com.asset.management.VO.PageViewVo;
import com.asset.management.VO.ResponseVO;

public interface AssetAssignDao {
	ResponseVO assetAssigned(Long assetId, AssetAssignVO assetAssignVO);

	ResponseVO returnDevice(Long assetId, AssetAssignVO assetAssignVO);

	List<AssetAssignVO> getAll(Long empNo);
	
	ListPageData searchEmployeeDate(@RequestBody PageViewVo page);
}

