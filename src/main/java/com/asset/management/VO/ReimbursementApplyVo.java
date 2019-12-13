package com.asset.management.VO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReimbursementApplyVo {

	
	Long reimbursementId;
	String empNo;
	String reimbursementDate;
	double totalCost;
	String onbtnClick;
	List<ReimbursementVo> reimbursementDetails;
	String reimbursementBills;
	MultipartFile[] imageData;

	public String getReimbursementBills() {
		return reimbursementBills;
	}

//	public void setReimbursementBills(String reimbursementBills) {
//	    List<ReimbursementVo> billData =  new ArrayList<>();
//		String bills = reimbursementBills;
//		Object obj = JSONValue.parse(bills);
//		JSONArray array = (JSONArray) obj;
//
//		for (int i = 0; i < array.size(); i++) {
//			ReimbursementVo bill = new ReimbursementVo();
//			JSONObject jsonObject1 = (JSONObject) array.get(i);
//			bill.setBillDate((String) jsonObject1.get("billDate"));
//			bill.setReimbursementDescription((String) jsonObject1.get("reimbursementDescription"));
//			bill.setCategoryName((String) jsonObject1.get("categoryName"));
//			BigInteger billNo=new BigInteger((String) jsonObject1.get("billNo"));
//			bill.setBillNo(billNo);
//			Double cost = Double.valueOf((String)jsonObject1.get("cost"));
//			bill.setCost(cost);
//			System.out.println(bill);
//			billData.add(bill);
//			reimbursementDetails.add(bill);
//			
//		}
//          System.out.println(billData);
//          
//          List<ReimbursementDetails> reimbursementDetails1=reimbursementMapper.voListConvert(billData);
//          
//	}

}
