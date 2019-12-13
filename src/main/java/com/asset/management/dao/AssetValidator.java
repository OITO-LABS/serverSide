
package com.asset.management.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.asset.management.VO.ResponseVO;
import com.asset.management.dao.entity.AssetEntity;
import com.asset.management.dao.repository.AssetRepository;
import com.asset.management.exception.ErrorCode;

@Component
public class AssetValidator implements Validation {

	private ErrorCode error;
	@Autowired
	private AssetRepository assetRepository;

	@Override
	public ResponseVO addValidation(AssetEntity asset) {
		final ResponseVO response = new ResponseVO();
		System.out.print(asset.getAssetKey());
		final AssetEntity dummy = assetRepository.findByAssetKey(asset.getAssetKey());
		if (dummy != null) {
			//response.setErrorcode(HttpStatus.BAD_REQUEST);
			response.setMessage("Asset Key already exist");
			response.setStatus("Failed");
		} else {
			response.setStatus("success");

		}

		return response;
	}

	@Override
	public ResponseVO deleteValidation(Long assetId) {
		final ResponseVO response = new ResponseVO();
		final AssetEntity asset = assetRepository.findByAssetId(assetId);
		if (asset != null) {
			response.setMessage("Asset Key already exist");
			//response.setErrorcode(HttpStatus.METHOD_NOT_ALLOWED);
			response.setStatus("failed");
			return response;
		} else {
			response.setStatus("success");
			return response;
		}

	}

	@Override
	public ResponseVO updateValidation(Long assetId, AssetEntity asset) {
		final ResponseVO response = new ResponseVO();
		final AssetEntity assetEntity = assetRepository.findByAssetId(assetId);
		if (assetEntity != null) {
			if (asset.getAssetKey() != null && asset.getProductCategory() != null && asset.getModel() != null) {
				if (asset.getProductCategory().equals("WiFi")) {
					if (asset.getMacId() != null && asset.getMsisdnNo() != null) {
						response.setStatus("success");
					}
				} else if (asset.getProductCategory().equals("Modem")) {
					if (asset.getMsisdnNo() != null) {
						response.setStatus("success");
					}
				} else {
					response.setStatus("success");
				}

			}
		} else {
			//response.setErrorcode(HttpStatus.FORBIDDEN);
			response.setStatus("failed");
			response.setMessage("You are missed any of the values for Product Category & Model");
		}
		return response;
	}

}

