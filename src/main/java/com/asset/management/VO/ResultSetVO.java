
package com.asset.management.VO;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResultSetVO {
	public ResultSetVO(Object temp) {
		assetId = getAssetId();

	}

	private BigInteger getAssetId() {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultSetVO() {

	}

	private BigInteger assetId;
	private String assetKey;
	private String productCategoryName;
	private String status;
	private String model;
	private BigInteger empId;
	private String empNo;

}

