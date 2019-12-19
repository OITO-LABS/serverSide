package com.asset.management.dao;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import com.asset.management.VO.CategoryVo;
import com.asset.management.VO.ListBillVo;
import com.asset.management.VO.ListPageData;
import com.asset.management.VO.PageViewVo;
import com.asset.management.VO.ReimbursementApplyVo;
import com.asset.management.VO.ReimbursementTrackVo;
import com.asset.management.VO.ResponseVO;
import com.asset.management.VO.TempVo;

public interface ReimbursementDao {

	ResponseVO applyReimbursement(ReimbursementApplyVo data) throws Exception;

	List<CategoryVo> getCategoryDetails();

	ListBillVo getReimbusementDetails(Long reimbursement_id);

	List<ReimbursementTrackVo> getReimbusrmentId();

	ListPageData getByDate(PageViewVo date);

	ListPageData reimbursementGetEmpDetails(@RequestBody PageViewVo page);

	ListPageData viewData(PageViewVo page);
	
	ListPageData searchEmployeeId(@RequestBody PageViewVo page);
	
	ListPageData searchEmployeeDate(@RequestBody PageViewVo page);
	
	public ResponseVO sendForApproval(TempVo data) throws Exception;
	
	public ResponseVO  deleteBill(TempVo data) throws Exception;
	
	public ResponseVO addBill(TempVo data) throws Exception;
	
	public ResponseVO updateBill(ReimbursementApplyVo data)throws Exception;
	
	ResponseVO billApproval(@RequestBody PageViewVo page)throws Exception;
	
	public ListBillVo getBill(Long reimbursementId);
	
	public ResponseVO verifyBill(PageViewVo page)throws Exception;

}




