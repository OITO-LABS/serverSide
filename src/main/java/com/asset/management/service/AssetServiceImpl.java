
package com.asset.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.asset.management.VO.AssetDetailsVO;
import com.asset.management.VO.AssetVO;
import com.asset.management.VO.PageData;
import com.asset.management.VO.PaginationVO;
import com.asset.management.VO.ProductCategoryVO;
import com.asset.management.VO.ResponseVO;
import com.asset.management.dao.AssetDaoImpl;

@Controller
public class AssetServiceImpl implements AssetService {
	@Autowired
	private AssetDaoImpl assetDaoImpl;

	@Override
	public ResponseVO create(AssetVO assetVO) {
		return assetDaoImpl.create(assetVO);
	}

	@Override
	public List<ProductCategoryVO> productCategoryDetails() {
		return assetDaoImpl.productCategoryDetails();
	}

	@Override
	public PageData getAssetDetails(PaginationVO paginationVO) {

		return assetDaoImpl.getAssetDetails(paginationVO);
	}

	@Override
	public ResponseVO deleteAsset(Long id,AssetVO assetVo) {
		return assetDaoImpl.deleteAsset(id,assetVo);
	}

	@Override
	public List<AssetDetailsVO> getById(Long id) {
		return assetDaoImpl.getById(id);
	}

	@Override
	public PageData getAllAsset(PaginationVO paginationVO) {

		return assetDaoImpl.getAllAsset(paginationVO);
	}

	@Override
	public List<AssetDetailsVO> getAssetHistory(Long assetId) {
		return assetDaoImpl.getAssetHistory(assetId);
	}

	@Override
	public ResponseVO updateAssetDetails(Long assetId, AssetVO assetVO) {
		return assetDaoImpl.updateAssetDetails(assetId, assetVO);
	}

	@Override
	public AssetVO getAssetById(Long assetId) {
		return assetDaoImpl.getAssetById(assetId);
	}

	@Override
	public ResponseVO activateAsset(Long assetId) throws Exception {
		return assetDaoImpl.activateAsset(assetId);
	}

}

