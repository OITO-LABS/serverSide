
package com.asset.management.VO;

import java.util.Date;

import com.asset.management.dao.entity.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AssetVO {
	private Long assetId;
	private String assetKey;
	private String model;
	private String macId;
	private String simNo;
	private String msisdnNo;
	private Status enableStatus;
	private String productCategory;
	private Long employeeId;
	private String updatedId;
	private Date addDate;
	private Date updatedDate;
}

