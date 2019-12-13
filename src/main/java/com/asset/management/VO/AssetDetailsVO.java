
package com.asset.management.VO;

import java.math.BigInteger;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AssetDetailsVO {
	private BigInteger assetId;
	private String assetKey;
	private String productCategoryName;
	private String status;
	private String macId;
	private String simNo;
	private String msisdnNO;
	private String model;
	private BigInteger empId;
	private String empNo;
	private String fname;
	private String lname;
	private String issueDate;
	private Date returnDate;
	private String cause;
}

