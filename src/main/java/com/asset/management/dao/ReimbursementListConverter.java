package com.asset.management.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.asset.management.VO.ListReimbursementVo;
import com.asset.management.VO.ReimbursementVo;
import com.asset.management.dao.entity.Status;

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
			String status=result[4].toString();
			if(((String)result[4]).equals("Save"))
			{
		    reimbursementViewVO.setReimbursementStatus(Status.Save);	
			}
			else if(((String)result[4]).equals("Pending"))
			{
				reimbursementViewVO.setReimbursementStatus(Status.Pending);
			}
			else if(((String)result[4]).equals("Approved"))
			{
				reimbursementViewVO.setReimbursementStatus(Status.Approved);
			}
			else if(((String)result[4]).equals("Rejected"))
			{
				reimbursementViewVO.setReimbursementStatus(Status.Rejected);
			}
			//reimbursementViewVO.setBillStatus(status);
			resultSetVO.add(reimbursementViewVO);
		}

		return resultSetVO;
	}

	public static List<ReimbursementVo> reConvertionBill(List<Object[]> resultSet) {
		final List<ReimbursementVo> resultSetVO = new ArrayList<>();
		for (final Object[] result : resultSet) {
			final ReimbursementVo reimbursementVo = new ReimbursementVo();
			reimbursementVo.setTrackId(((BigInteger)result[0]).longValue());
			reimbursementVo.setReimbursementTrack(((BigInteger)result[1]).longValue());
			reimbursementVo.setBillDate((formatter.format((Date) result[2])).toString());
			reimbursementVo.setReimbursementDescription((String) result[3]);
			reimbursementVo.setBillNo((BigInteger) result[4]);
			reimbursementVo.setCost((double) result[5]);
			reimbursementVo.setCategoryName((String) result[6]);
			String status=result[7].toString();
			if(((String)result[7]).equals("Save"))
			{
			reimbursementVo.setBillStatus(Status.Save);
			}
			else if(result[7].equals("Pending"))
			{
				reimbursementVo.setBillStatus(Status.Pending);
			}
			else if(((String)result[4]).equals("Approved"))
			{
				reimbursementVo.setBillStatus(Status.Approved);
			}
			else if(((String)result[4]).equals("Rejected"))
			{
				reimbursementVo.setBillStatus(Status.Rejected);
			}
			resultSetVO.add(reimbursementVo);
		}
		
		return resultSetVO;
	}

}
