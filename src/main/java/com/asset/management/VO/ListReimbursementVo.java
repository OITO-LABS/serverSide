
package com.asset.management.VO;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ListReimbursementVo {

	BigInteger reimbursementId;
	String reimbursementDate;
	String empNo;
	double totalCost;
}

