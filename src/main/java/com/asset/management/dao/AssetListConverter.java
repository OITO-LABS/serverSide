
package com.asset.management.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.asset.management.VO.AssetDetailsVO;
import com.asset.management.VO.ResultSetVO;

public class AssetListConverter<T> {
	// private static PageData data = new PageData();

	public static List<ResultSetVO> reConvertion(List<Object[]> resultSet) {
		final List<ResultSetVO> resultSetVO = new ArrayList<>();
		for (final Object[] result : resultSet) {
			final ResultSetVO assetVO = new ResultSetVO();
			assetVO.setAssetId((BigInteger) result[0]);
			assetVO.setAssetKey((String) result[1]);
			assetVO.setProductCategoryName((String) result[2]);
			assetVO.setStatus((String) result[3]);
			assetVO.setModel((String) result[4]);
			assetVO.setEmpId((BigInteger) result[5]);
			assetVO.setEmpNo((String) result[6]);

			resultSetVO.add(assetVO);
		}
		return resultSetVO;
	}

	public static List<AssetDetailsVO> assetConverter(List<Object[]> resultSet) {
		final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		final List<AssetDetailsVO> assetDetails = new ArrayList<>();
		for (final Object[] result : resultSet) {
			final AssetDetailsVO asset = new AssetDetailsVO();
			asset.setAssetId((BigInteger) result[0]);
			if (result[6].equals("Assigned")) {
				asset.setAssetKey((String) result[1]);
				asset.setProductCategoryName((String) result[2]);
				asset.setMacId((String) result[3]);
				asset.setSimNo((String) result[4]);
				asset.setMsisdnNO((String) result[5]);
				asset.setStatus((String) result[6]);
				asset.setModel((String) result[7]);
				asset.setEmpId((BigInteger) result[8]);
				asset.setEmpNo((String) result[9]);
				asset.setFname((String) result[10]);
				asset.setLname((String) result[11]);
				asset.setIssueDate((formatter.format((Date) result[12])).toString());
				asset.setReturnDate((Date) result[13]);
				asset.setCause((String) result[14]);
			} else {
				asset.setAssetKey((String) result[1]);
				asset.setProductCategoryName((String) result[2]);
				asset.setMacId((String) result[3]);
				asset.setSimNo((String) result[4]);
				asset.setMsisdnNO((String) result[5]);
				asset.setStatus((String) result[6]);
				asset.setModel((String) result[7]);
				asset.setEmpId(null);
				asset.setEmpNo(null);
				asset.setFname(null);
				asset.setLname(null);
				asset.setIssueDate(null);
				asset.setReturnDate(null);
				asset.setCause(null);
			}
			assetDetails.add(asset);
		}
		return assetDetails;
	}

	public static List<AssetDetailsVO> historyConverter(List<Object[]> resultSet) {
		final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		final List<AssetDetailsVO> assetDetails = new ArrayList<>();
		for (final Object[] result : resultSet) {
			final AssetDetailsVO asset = new AssetDetailsVO();
			asset.setAssetId((BigInteger) result[0]);
			asset.setEmpId((BigInteger) result[1]);
			asset.setEmpNo((String) result[2]);
			asset.setFname((String) result[3]);
			asset.setLname((String) result[4]);
			if (result[5].equals(null)) {
				asset.setIssueDate(null);
			} else {
				asset.setIssueDate((formatter.format((Date) result[5])));
			}

			asset.setReturnDate((Date) result[6]);
			asset.setCause((String) result[7]);
			assetDetails.add(asset);
		}
		return assetDetails;
	}

	@SuppressWarnings("unchecked")
	public static List<ResultSetVO> pageConvertion(@SuppressWarnings("rawtypes") List list) {
		final List<ResultSetVO> resultSetVO = new ArrayList<>();
		list.forEach((temp) -> {
			System.out.println(temp);
			@SuppressWarnings("unused")
			final ResultSetVO assetVO = new ResultSetVO(temp);

		});
		return resultSetVO;
	}

}

