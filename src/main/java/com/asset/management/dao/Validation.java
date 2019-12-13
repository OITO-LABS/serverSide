
package com.asset.management.dao;

import com.asset.management.VO.ResponseVO;
import com.asset.management.dao.entity.AssetEntity;

public interface Validation {
	ResponseVO addValidation(AssetEntity asset);

	ResponseVO deleteValidation(Long assetId);

	ResponseVO updateValidation(Long assetId, AssetEntity asset);
}

