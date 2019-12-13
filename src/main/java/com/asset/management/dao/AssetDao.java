
package com.asset.management.dao;

import java.util.List;

import com.asset.management.VO.AssetDetailsVO;
import com.asset.management.VO.AssetVO;
import com.asset.management.VO.PageData;
import com.asset.management.VO.PaginationVO;
import com.asset.management.VO.ProductCategoryVO;
import com.asset.management.VO.ResponseVO;

public interface AssetDao {

	ResponseVO create(AssetVO assetVO);

	List<ProductCategoryVO> productCategoryDetails();

	ResponseVO deleteAsset(Long id,AssetVO assetVo);

	List<AssetDetailsVO> getById(Long id);

	PageData getAllAsset(PaginationVO paginationVO);

	PageData getAssetDetails(PaginationVO paginationVO);

	List<AssetDetailsVO> getAssetHistory(Long assetId);

	ResponseVO updateAssetDetails(Long assetId, AssetVO assetVO);

	AssetVO getAssetById(Long assetId);
	
	 ResponseVO activateAsset(Long assetId) throws Exception;
}
