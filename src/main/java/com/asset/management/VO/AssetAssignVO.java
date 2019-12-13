
package com.asset.management.VO;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AssetAssignVO {
	private AssetVO assetAssign;
	private EmployeeVo employee;
	private String issueDate;
	private Date returnDate;
	private String cause;
	private String assignStatus;
	private Long updatedId;
	private Date addDate;
	private Date updatedDate;
}

