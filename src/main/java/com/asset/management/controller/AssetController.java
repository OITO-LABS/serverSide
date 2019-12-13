
package com.asset.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asset.management.VO.AssetDetailsVO;
import com.asset.management.VO.AssetVO;
import com.asset.management.VO.PageData;
import com.asset.management.VO.PaginationVO;
import com.asset.management.VO.ProductCategoryVO;
import com.asset.management.VO.ResponseVO;
import com.asset.management.exception.InstallationException;
import com.asset.management.service.AssetServiceImpl;

@RestController
@RequestMapping("api/asset")
public class AssetController {
	@Autowired
	AssetServiceImpl assetServiceImpl;

	@GetMapping
	public String getString() {
		try {
			return ("getString executed");
		} catch (final Exception ex) {
			final ResponseVO response = new ResponseVO();
			response.setStatus("failed");
			response.setMessage(ex.getMessage());
			throw new InstallationException(ex);
		}
	}

	@GetMapping("/{assetId}")
	public List<AssetDetailsVO> getAssetHistory(@PathVariable Long assetId) {
		try {
			return assetServiceImpl.getAssetHistory(assetId);
		} catch (final Exception ex) {
			final ResponseVO response = new ResponseVO();
			response.setStatus("failed");
			response.setMessage(ex.getMessage());
			throw new InstallationException(ex);

		}
	}

	@PostMapping
	public Object create(@RequestBody AssetVO assetVo) {
		try {
			return assetServiceImpl.create(assetVo);
		} catch (final Exception ex) {
			final ResponseVO response = new ResponseVO();
			response.setStatus("failed");
			response.setMessage(ex.getMessage());
			throw new InstallationException(ex);

		}

	}

	@GetMapping("productCategory")
	public List<ProductCategoryVO> getProductDetails() {
		try {
			return assetServiceImpl.productCategoryDetails();
		} catch (final Exception ex) {
			final ResponseVO response = new ResponseVO();
			response.setStatus("failed");
			response.setMessage(ex.getMessage());
			throw new InstallationException(ex);

		}

	}

	@GetMapping("getforupdate/details/{assetId}")
	public AssetVO getassetById(@PathVariable Long assetId) {
		try {
			return assetServiceImpl.getAssetById(assetId);
		} catch (final Exception ex) {
			final ResponseVO response = new ResponseVO();
			response.setStatus("failed");
			response.setMessage(ex.getMessage());
			throw new InstallationException(ex);

		}
	}

	@GetMapping("details/{assetId}")
	public List<AssetDetailsVO> getById(@PathVariable Long assetId) {
		try {
			return assetServiceImpl.getById(assetId);
		} catch (final Exception ex) {
			final ResponseVO response = new ResponseVO();
			response.setStatus("failed");
			response.setMessage(ex.getMessage());
			throw new InstallationException(ex);

		}
	}

	@PostMapping("assetDetails")
	public PageData getAssetDetails(@RequestBody PaginationVO paginationVO) {
		try {
			return assetServiceImpl.getAssetDetails(paginationVO);
		} catch (final Exception ex) {
			final ResponseVO response = new ResponseVO();
			response.setStatus("failed");
			response.setMessage(ex.getMessage());
			throw new InstallationException(ex);

		}
	}

	@PostMapping("getAll")
	public PageData getAll(@RequestBody PaginationVO paginationVO) {
		try {
			return assetServiceImpl.getAllAsset(paginationVO);
		} catch (final Exception ex) {
			final ResponseVO response = new ResponseVO();
			response.setStatus("failed");
			response.setMessage(ex.getMessage());
			throw new InstallationException(ex);

		}
	}

	@PutMapping("/delete/{id}")
	public ResponseVO deleteAsset(@PathVariable Long id,@RequestBody AssetVO assetVo) {
		try {
			return assetServiceImpl.deleteAsset(id,assetVo);
		} catch (final Exception ex) {
			final ResponseVO response = new ResponseVO();
			response.setStatus("failed");
			response.setMessage(ex.getMessage());
			throw new InstallationException(ex);

		}

	}

	@PutMapping("/{assetId}")
	public ResponseVO updateAsset(@PathVariable Long assetId, @RequestBody AssetVO assetVO) {
		try {
			return assetServiceImpl.updateAssetDetails(assetId, assetVO);
		} catch (final Exception ex) {
			final ResponseVO response = new ResponseVO();
			response.setStatus("failed");
			response.setMessage(ex.getMessage());
			throw new InstallationException(ex);

		}
	}
	
	@PutMapping("activate/{assetId}")
	public ResponseVO activateAsset(@PathVariable Long assetId) {
		final ResponseVO response = new ResponseVO();
		try {
			assetServiceImpl.activateAsset(assetId);
			response.setStatus("success");
			response.setMessage("Activated again");
			return response;
		}
		catch(Exception ex) {
			response.setStatus("failed");
			throw new InstallationException(ex);
		}
	}
}

