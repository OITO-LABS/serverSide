
package com.asset.management.VO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.asset.management.VO.mapping.ReimbursementMapper;
import com.asset.management.dao.ReimbursementListConverter;
import com.asset.management.dao.entity.ReimbursementDetails;
import com.asset.management.dao.entity.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReimbursementTrackVo {

	Long reimbursementId;
	String empNo;
	String reimbursementDate;
	double totalCost;
	Status reimbursementStatus;
	List<ReimbursementVo> reimbursementDetails;

}
