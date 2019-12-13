package com.asset.management.VO;

import java.math.BigInteger;

import com.asset.management.dao.entity.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class ReimbursementVo {

	Long trackId;
	String billDate;
	String reimbursementDescription;
	String categoryName;
	BigInteger billNo;
	double cost;
	Status billStatus;


}