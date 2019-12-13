
package com.asset.management.VO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ListBillVo {

	private List<ReimbursementVo> billDetails;
	double totalCost;
	String empName;
	String empNo;
	String empDesignation;

}