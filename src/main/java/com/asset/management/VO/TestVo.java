package com.asset.management.VO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestVo {

	
	Long reimbursementId;
	String empNo;
	String reimbursementDate;
	double totalCost;
	List<ReimbursementVo> reimbursementDetails;
	MultipartFile[] imageData;

}
