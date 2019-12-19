package com.asset.management.controller;

import java.io.IOException;

import java.util.List;




import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asset.management.VO.CategoryVo;
import com.asset.management.VO.ListBillVo;
import com.asset.management.VO.ListPageData;
import com.asset.management.VO.PageViewVo;
import com.asset.management.VO.ReimbursementApplyVo;
import com.asset.management.VO.ReimbursementTrackVo;
import com.asset.management.VO.ReimbursementVo;
import com.asset.management.VO.ResponseVO;
import com.asset.management.VO.TempVo;
import com.asset.management.VO.TestVo;
import com.asset.management.service.ReimbursementService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("api/reimbursement")
public class ReimbursementController {
	@Autowired
	private ReimbursementService reimbursementService;

	@PostMapping("apply") // ResponseVO data//ReimbursementTrackVo
	public ResponseVO applyReimbursement(@ModelAttribute ReimbursementApplyVo data) throws JsonParseException, JsonMappingException, IOException {
		ResponseVO response=new ResponseVO();
		try {
		MultipartFile[] file = data.getImageData();
		System.out.println(data.getEmpNo());
	    System.out.println(data.getReimbursementDetails());
		for (MultipartFile files : data.getImageData()) {
			String resp = files.getOriginalFilename();
			System.out.println(resp);
		}
		response.setStatus("success");
		
		return  reimbursementService.applyReimbursement(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.setStatus("failed");
			response.setMessage("Bill can't register");
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("list-category")
	public List<CategoryVo> selectAll() {
		return reimbursementService.getCategoryDetails();
	}

	@GetMapping("emp-id/{reimbursementId}")
	public ListBillVo get(@PathVariable Long reimbursementId) {
		return reimbursementService.getReimbursementDetails(reimbursementId);

	}
	
	@GetMapping("admin/{reimbursementId}")
	public ListBillVo getBill(@PathVariable Long reimbursementId) {
		return reimbursementService.getBill(reimbursementId);

	}

	@PostMapping("reimbursement-list")
	public ListPageData viewData(@RequestBody PageViewVo page) {
		return reimbursementService.viewData(page);
	}

	@PostMapping("reimbursement-date")
	public ListPageData getByDate(@RequestBody PageViewVo date) {
		return reimbursementService.getByDate(date);
	}

	@PostMapping("emp-id")
	public ListPageData get(@RequestBody PageViewVo page) {
		return reimbursementService.reimbursementGetEmpDetails(page);

	}

	@PostMapping("search-emp-id")
	public ListPageData searchEmployeeId(@RequestBody PageViewVo page) {

		return reimbursementService.searchEmployee(page);

	}

	@PostMapping("search-emp-id-date")
	public ListPageData searchEmployeeDate(@RequestBody PageViewVo page) {

		return reimbursementService.searchEmployeeDate(page);
	}

	@PostMapping("send-approval")
	public ResponseVO sendForApproval(@RequestBody TempVo data) {
		ResponseVO response=new ResponseVO();
		try {
			response=reimbursementService.sendForApproval(data);
		}
		catch(Exception e){
			response.setStatus("failed");
			response.setMessage("Bill approve operation failed");
			e.printStackTrace();
		}
		
		return response;
	}

	@PostMapping("delete-bill")
	public ResponseVO deleteBill(@RequestBody TempVo data) {
		ResponseVO response=new ResponseVO();
		try {
			response=reimbursementService.deleteBill(data);
		} catch (Exception e) {
			response.setStatus("failed");
			response.setMessage("Deletion failed");
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping("add-bill")
	public ResponseVO addBill(@RequestBody TempVo data) {
		ResponseVO response=new ResponseVO();
		try {
			response=reimbursementService.addBill(data);;
		}
		catch(Exception e){
			response.setStatus("failed");
			response.setMessage("Deletion failed");
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping("update-bill")
	public ResponseVO updateBill(@ModelAttribute ReimbursementApplyVo data) {
		ResponseVO response=new ResponseVO();
		try {
			response=reimbursementService.updateBill(data);
		}
		catch(Exception e) {
			response.setStatus("failed");
			response.setMessage("Updation failed");
			e.printStackTrace();
		}
		return response;
	}
	
	@PostMapping("verify-bill")
	public ResponseVO verifyBill(@RequestBody PageViewVo page) {
		ResponseVO response=new ResponseVO();
		try {
			response=reimbursementService.verifyBill(page);
		}
		catch(Exception e) {
			response.setStatus("failed");
			response.setMessage("Verification failed");
			e.printStackTrace();
		}
		return response;
		
	}
	
	@PostMapping("admin-action")
	public ResponseVO billApproval(@RequestBody PageViewVo page)
	{
		ResponseVO response=new ResponseVO();
		try {
			response=reimbursementService.billApproval(page);
		}
		catch(Exception e) {
			response.setStatus("failed");
			response.setMessage("Verification failed");
			e.printStackTrace();
		}
		return response;
	}
	
	

}

