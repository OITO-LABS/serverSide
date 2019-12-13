
package com.asset.management.dao;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.asset.management.VO.ReimbursementTrackVo;
import com.asset.management.VO.ReimbursementVo;
import com.asset.management.VO.ResponseVO;
import com.asset.management.dao.entity.ReimbursementDetails;
import com.asset.management.dao.repository.ReimbursementRepository;

@Component
public class ReimbursementValidator implements Validator {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ReimbursementValidator.class);

	@Autowired
	ReimbursementRepository reimbursementRepository;

	@Override
	public ResponseVO validate(ReimbursementTrackVo data) {
		double calcCost = 0;
		int status = 0;

		final ResponseVO response = new ResponseVO();
		final List<ReimbursementVo> billData = data.getReimbursementDetails();
		final int listSize = billData.size();
		for (int i = 0; i < listSize; i++) {
			final ReimbursementVo listData = billData.get(i);
			final ReimbursementDetails test = reimbursementRepository.findByReimbursementBillNo(listData.getBillNo());
			if (test != null) {
				//response.setErrorcode(HttpStatus.BAD_REQUEST);
				response.setStatus("Failed");
				response.setMessage("BillNo already Exists");
				status = 1;
			} else {
				response.setMessage("success");
			}
		}
		if (status == 0) {
			for (int i = 0; i < listSize; i++) {
				final ReimbursementVo costData = billData.get(i);
				calcCost = calcCost + costData.getCost();
			}
			if (calcCost == data.getTotalCost()) {
				logger.info("Cost Match");
				response.setStatus("success");

			} else {
				//response.setErrorcode(HttpStatus.BAD_REQUEST);
				response.setStatus("Failed");
				response.setMessage("Total cost error ");
			}
		}

		return response;
	}
}
