
package com.asset.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asset.management.VO.AssetAssignVO;
import com.asset.management.VO.ResponseVO;
import com.asset.management.dao.AssetAssignDao;

@Service
public class AssetAssignImpl implements AssetAssign {
	@Autowired
	AssetAssignDao assetAssign;

	@Override
	public ResponseVO assetAssigned(Long assetId, AssetAssignVO assetAssignVO) {
		return assetAssign.assetAssigned(assetId, assetAssignVO);
	}

	@Override
	public ResponseVO assetReturn(Long assetId, AssetAssignVO assetAssignVO) {
		return assetAssign.returnDevice(assetId, assetAssignVO);
	}

	@Override
	public List<AssetAssignVO> getAll(Long id) {
		return assetAssign.getAll(id);
	}

}

