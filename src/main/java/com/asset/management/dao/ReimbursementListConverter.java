package com.asset.management.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.asset.management.VO.ListReimbursementVo;
import com.asset.management.VO.ReimbursementVo;

public class ReimbursementListConverter {

	final static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	public static List<ListReimbursementVo> reConvertion(List<Object[]> resultSet) {
		final List<ListReimbursementVo> resultSetVO = new ArrayList<>();
		for (final Object[] result : resultSet) {
			final ListReimbursementVo reimbursementViewVO = new ListReimbursementVo();
			reimbursementViewVO.setReimbursementId((BigInteger) result[0]);
			reimbursementViewVO.setReimbursementDate((formatter.format((Date) result[2])).toString());
			reimbursementViewVO.setEmpNo((String) result[1]);
			reimbursementViewVO.setTotalCost((double) result[3]);
			resultSetVO.add(reimbursementViewVO);
		}

		return resultSetVO;
	}

	public static List<ReimbursementVo> reConvertionBill(List<Object[]> resultSet) {
		final List<ReimbursementVo> resultSetVO = new ArrayList<>();
		for (final Object[] result : resultSet) {
			final ReimbursementVo reimbursementVo = new ReimbursementVo();
			reimbursementVo.setBillDate((formatter.format((Date) result[2])).toString());
			reimbursementVo.setReimbursementDescription((String) result[3]);
			reimbursementVo.setBillNo((BigInteger) result[4]);
			reimbursementVo.setCost((double) result[5]);
			reimbursementVo.setCategoryName((String) result[6]);
			resultSetVO.add(reimbursementVo);
		}

		return resultSetVO;
	}

}
