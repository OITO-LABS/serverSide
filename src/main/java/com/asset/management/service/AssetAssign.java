
package com.asset.management.service;

import java.util.List;

import com.asset.management.VO.AssetAssignVO;
import com.asset.management.VO.ResponseVO;

public interface AssetAssign {
	ResponseVO assetAssigned(Long assetId, AssetAssignVO assetAssignVO);

	ResponseVO assetReturn(Long assetId, AssetAssignVO assetAssignVO);

	List<AssetAssignVO> getAll(Long id);

}

