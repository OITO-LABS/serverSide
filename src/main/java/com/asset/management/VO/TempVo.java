package com.asset.management.VO;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TempVo {

	Long reimbursementId;
	Long billId;
	String billDate;
	String reimbursementDescription;
	String categoryName;
	BigInteger billNo;
	double cost;
}
