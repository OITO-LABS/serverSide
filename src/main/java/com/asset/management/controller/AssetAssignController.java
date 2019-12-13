
package com.asset.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asset.management.VO.AssetAssignVO;
import com.asset.management.VO.ResponseVO;
import com.asset.management.exception.InstallationException;
import com.asset.management.service.AssetAssign;

@RestController
@RequestMapping("api/assetassign")
public class AssetAssignController {
	@Autowired
	private AssetAssign assetAssign;

	@GetMapping("/{id}")
	List<AssetAssignVO> getAll(@PathVariable Long id) {
		try {
			
			return assetAssign.getAll(id);
		} catch (final Exception ex) {
			final ResponseVO response = new ResponseVO();
			response.setStatus("failed");
			response.setMessage(ex.getMessage());
			throw new InstallationException(ex);

		}
	}

	// Assign asset
	@PostMapping("{assetId}/{status}")
	private ResponseVO assetAssign(@PathVariable Long assetId, @PathVariable String status,
			@RequestBody AssetAssignVO assetAssignVO) {
		try {
			return assetAssign.assetAssigned(assetId, assetAssignVO);
		} catch (final Exception ex) {
			final ResponseVO response = new ResponseVO();
			response.setStatus("failed");
			response.setMessage(ex.getMessage());
			throw new InstallationException(ex);

		}
	}

	// Return asset details
	@PutMapping("{assetId}/{status}")
	ResponseVO returnAsset(@PathVariable Long assetId, @PathVariable String status,
			@RequestBody AssetAssignVO assetAssignVO) {
		try {
			return assetAssign.assetReturn(assetId, assetAssignVO);
		} catch (final Exception ex) {
			final ResponseVO response = new ResponseVO();
			response.setStatus("failed");
			response.setMessage(ex.getMessage());
			throw new InstallationException(ex);

		}

	}

}