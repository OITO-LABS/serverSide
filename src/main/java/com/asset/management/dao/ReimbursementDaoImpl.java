
package com.asset.management.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.asset.management.VO.CategoryVo;
import com.asset.management.VO.ListBillVo;
import com.asset.management.VO.ListPageData;
import com.asset.management.VO.PageViewVo;
import com.asset.management.VO.ReimbursementApplyVo;
import com.asset.management.VO.ReimbursementTrackVo;
import com.asset.management.VO.ReimbursementVo;
import com.asset.management.VO.ResponseVO;
import com.asset.management.VO.TempVo;
import com.asset.management.VO.mapping.CategoryMapper;
import com.asset.management.VO.mapping.ReimbursementMapper;
import com.asset.management.VO.mapping.ReimbursementTrackMapper;
import com.asset.management.dao.entity.CategoryDetails;
import com.asset.management.dao.entity.Employee;
import com.asset.management.dao.entity.ReimbursementDetails;
import com.asset.management.dao.entity.ReimbursementTrack;
import com.asset.management.dao.entity.Status;
import com.asset.management.dao.repository.CategoryRepository;
import com.asset.management.dao.repository.EmployeeRepository;
import com.asset.management.dao.repository.ReimbursementRepository;
import com.asset.management.dao.repository.ReimbursementTrackRepository;

@Component

public class ReimbursementDaoImpl implements ReimbursementDao {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ReimbursementDaoImpl.class);

	ReimbursementListConverter listConverter;

	@Autowired
	ReimbursementMapper reimbursementMapper;

	@Autowired
	ReimbursementTrackMapper reimbursementTrackMapper;

	@Autowired
	CategoryMapper categoryMapper;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	Validator reimbursementValidator;

	@Autowired
	ReimbursementRepository reimbursementRepository;

	@Autowired
	ReimbursementTrackRepository reimbursementTrackRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public ResponseVO applyReimbursement(ReimbursementApplyVo data) {

		logger.info("In Dao Class");
		logger.info("{}", data);
		MultipartFile[] file = data.getImageData();
		int fileSize=file.length;
		System.out.println(data.getReimbursementDetails());
		ReimbursementTrackVo trackData = new ReimbursementTrackVo();
		trackData.setEmpNo(data.getEmpNo());
		trackData.setReimbursementDate(data.getReimbursementDate());
		trackData.setTotalCost(data.getTotalCost());
		trackData.setReimbursementStatus(Status.Inactive);
		List<ReimbursementVo> billData = new ArrayList<>();
		String bills = data.getReimbursementBills();
		Object obj = JSONValue.parse(bills);
		JSONArray array = (JSONArray) obj;

		for (int i = 0; i < array.size(); i++) {
			ReimbursementVo bill = new ReimbursementVo();
			JSONObject jsonObject1 = (JSONObject) array.get(i);
			bill.setBillDate((String) jsonObject1.get("billDate"));
			bill.setReimbursementDescription((String) jsonObject1.get("reimbursementDescription"));
			bill.setCategoryName((String) jsonObject1.get("categoryName"));
			BigInteger billNo = new BigInteger((String) jsonObject1.get("billNo"));
			bill.setBillNo(billNo);
			String costCovert = (String) jsonObject1.get("cost");
			int x = Integer.parseInt(costCovert);
			double cost = ((double) x);
			bill.setCost(cost);
			bill.setBillStatus(Status.Save);
			System.out.println(bill);
			billData.add(bill);

		}
		trackData.setReimbursementDetails(billData);
		// --------------------------------------------------------------------------------
		System.out.println(billData);
		System.out.println(trackData);
		// ------------------------------------------------------------------------------------
		final ReimbursementTrack reimbursementTrack = reimbursementTrackMapper.voConversion(trackData);
		logger.info("{}", reimbursementTrack);
		final List<ReimbursementDetails> reimbursementDetails = reimbursementTrack.getReimbursementDetails();
		final int length = reimbursementDetails.size();
		logger.info(" list size " + length);
		for (int i = 0; i < length; i++) {
			if(data.getOnbtnClick().equals("save"))
			{
			reimbursementDetails.get(i).setBillStatus(Status.Save);
			}
			else if(data.getOnbtnClick().equals("submit"))
			{
				reimbursementDetails.get(i).setBillStatus(Status.Pending);
			}
				
		}

		final ResponseVO returnValue = reimbursementValidator.validate(trackData);

		if (returnValue.getMessage().equals("success")) {
			if(data.getOnbtnClick().equals("save"))
			{
				reimbursementTrack.setReimbursementStatus(Status.Inactive);
			}
			else if(data.getOnbtnClick().equals("submit"))
			{
				reimbursementTrack.setReimbursementStatus(Status.Pending);
			}
			reimbursementTrackRepository.save(reimbursementTrack);
			for (int i = 0; i < length; i++) {	
			File convertFile = new File("src/main/resources/public/bills/" +reimbursementDetails.get(i).getBillNo()+"."+file[i].getOriginalFilename().substring(file[i].getOriginalFilename().lastIndexOf(".") + 1));
			String path="src/main/resources/Bills"+file[i].getOriginalFilename();
			try {
				convertFile.createNewFile();
				FileOutputStream fout = new FileOutputStream(convertFile);
				fout.write(file[i].getBytes());

			} catch (IOException e) {

			}
			}
			logger.info("Data inserted Successfully");
		}

		return returnValue;
	}

	@Override
	public ListBillVo getReimbusementDetails(Long reimbursementId) {
		final List<Object[]> billData = reimbursementRepository.findByReimbursementTrack(reimbursementId);
		final ListBillVo listBill = new ListBillVo();
		listBill.setBillDetails(listConverter.reConvertionBill(billData));
		final ReimbursementTrack reimbursementData = reimbursementTrackRepository
				.findByReimbursementId(reimbursementId);
		final Employee empData = employeeRepository.findByEmpNo(reimbursementData.getEmpNo());
		listBill.setTotalCost(reimbursementData.getTotalCost());
		listBill.setEmpNo(empData.getEmpNo());
		listBill.setEmpName(empData.getFirstName() + " " + empData.getLastName());
		listBill.setEmpDesignation(empData.getDesignation());
		logger.info("{}", listBill);
		return listBill;
	}

	@Override
	public List<CategoryVo> getCategoryDetails() {
		final List<CategoryDetails> category = categoryRepository.selectCategory();
		final List<CategoryVo> categoryVo = categoryMapper.entityListConvert(category);
		logger.info("{}", categoryVo);
		return categoryVo;

	}

	@Override
	public List<ReimbursementTrackVo> getReimbusrmentId() {
		final List<ReimbursementTrack> data = reimbursementTrackRepository.getReimbursementId();
		return reimbursementTrackMapper.entityListConvert(data);
	}

	@Override
	public ListPageData getByDate(PageViewVo date) {

//		final Pageable pageable = PageRequest.of(date.getPage(), date.getSize(),
//				Sort.by("reimbursement_date").descending());
//		final Page data = reimbursementTrackRepository.getAllBetweenDates(pageable, date.getDateFrom(),
//				date.getDateTo());
//		

		Page data;
		if (date.getSortKey().equals("reimbursementDate")) {
			date.setSortKey("reimbursement_date");
		} else if (date.getSortKey().equals("totalCost")) {
			date.setSortKey("total_cost");
		} else if (date.getSortKey().equals("empNo")) {
			date.setSortKey("emp_no");
		}

		if (date.getSortKey().isBlank() && date.getSortOrder().isBlank()) {
			final Pageable pageable = PageRequest.of(date.getPage(), date.getSize(),
					Sort.by("reimbursement_date").descending());
			data = reimbursementTrackRepository.getAllBetweenDates(pageable, date.getDateFrom(), date.getDateTo());

		} else {

			if (date.getSortOrder().equals("ascending")) {

				final Pageable pageable = PageRequest.of(date.getPage(), date.getSize(),
						Sort.by(date.getSortKey()).ascending());
				data = reimbursementTrackRepository.getAllBetweenDates(pageable, date.getDateFrom(), date.getDateTo());
			} else {
				final Pageable pageable = PageRequest.of(date.getPage(), date.getSize(),
						Sort.by(date.getSortKey()).descending());
				data = reimbursementTrackRepository.getAllBetweenDates(pageable, date.getDateFrom(), date.getDateTo());
			}

		}

		final List<Object[]> dataList = data.getContent();
		final ListPageData pageData = new ListPageData();
		pageData.setReimbursementDetails(listConverter.reConvertion(dataList));
		pageData.setNumber(data.getNumber());
		pageData.setNumberOfElements(data.getNumberOfElements());
		pageData.setSize(data.getSize());
		pageData.setTotalElements(data.getTotalElements()); //
		pageData.setTotalPages(data.getTotalPages());
		logger.info("{}", pageData);
		return pageData;

	}

	@Override
	public ListPageData reimbursementGetEmpDetails(@RequestBody PageViewVo page) {
		final Pageable pageable = PageRequest.of(page.getPage(), page.getSize(),
				Sort.by("reimbursement_date").descending());
		final Page data = reimbursementTrackRepository.findByReimbursementEmpNo(pageable, page.getEmpNo());
		final List<Object[]> dataList = data.getContent();
		final ListPageData pageData = new ListPageData();
		pageData.setReimbursementDetails(listConverter.reConvertion(dataList));
		pageData.setNumber(data.getNumber());
		pageData.setNumberOfElements(data.getNumberOfElements());
		pageData.setSize(data.getSize());
		pageData.setTotalElements(data.getTotalElements()); //
		pageData.setTotalPages(data.getTotalPages());
		logger.info("{}", pageData);
		return pageData;
	}

	@Override
	public ListPageData viewData(PageViewVo page) {
		Page data;
		if (page.getSortKey().equals("reimbursementDate")) {
			page.setSortKey("reimbursement_date");
		} else if (page.getSortKey().equals("totalCost")) {
			page.setSortKey("total_cost");
		} else if (page.getSortKey().equals("empNo")) {
			page.setSortKey("emp_no");
		}

		if (page.getSortKey().isBlank() && page.getSortOrder().isBlank()) {
			final Pageable pageable = PageRequest.of(page.getPage(), page.getSize(),
					Sort.by("reimbursement_date").descending());
			data = reimbursementTrackRepository.getReimbursementView(pageable);

		} else {

			if (page.getSortOrder().equals("ascending")) {

				final Pageable pageable = PageRequest.of(page.getPage(), page.getSize(),
						Sort.by(page.getSortKey()).ascending());
				data = reimbursementTrackRepository.getReimbursementView(pageable);
			} else {
				final Pageable pageable = PageRequest.of(page.getPage(), page.getSize(),
						Sort.by(page.getSortKey()).descending());
				data = reimbursementTrackRepository.getReimbursementView(pageable);
			}

		}

		final List<Object[]> dataList = data.getContent();
		final ListPageData pageData = new ListPageData();
		pageData.setReimbursementDetails(listConverter.reConvertion(dataList));
		pageData.setPageable(data.getPageable());
		pageData.setNumber(data.getNumber());
		pageData.setNumberOfElements(data.getNumberOfElements());
		pageData.setSize(data.getSize());
		pageData.setTotalElements(data.getTotalElements());
		pageData.setTotalPages(data.getTotalPages());
		return pageData;
	}

	@Override
	public ListPageData searchEmployeeId(PageViewVo page) {

//		final Pageable pageable = PageRequest.of(page.getPage(), page.getSize(),
//				Sort.by("reimbursement_date").descending());
//		final Page data = reimbursementTrackRepository.findByReimbursementSearchEmpNo(page.getEmpNo(), pageable);

		Page data;
		if (page.getSortKey().equals("reimbursementDate")) {
			page.setSortKey("reimbursement_date");
		} else if (page.getSortKey().equals("totalCost")) {
			page.setSortKey("total_cost");
		} else if (page.getSortKey().equals("empNo")) {
			page.setSortKey("emp_no");
		}

		if (page.getSortKey().isBlank() && page.getSortOrder().isBlank()) {
			final Pageable pageable = PageRequest.of(page.getPage(), page.getSize(),
					Sort.by("reimbursement_date").descending());
			data = reimbursementTrackRepository.findByReimbursementSearchEmpNo(page.getEmpNo(), pageable);

		} else {

			if (page.getSortOrder().equals("ascending")) {

				final Pageable pageable = PageRequest.of(page.getPage(), page.getSize(),
						Sort.by(page.getSortKey()).ascending());
				data = reimbursementTrackRepository.findByReimbursementSearchEmpNo(page.getEmpNo(), pageable);
			} else {
				final Pageable pageable = PageRequest.of(page.getPage(), page.getSize(),
						Sort.by(page.getSortKey()).descending());
				data = reimbursementTrackRepository.findByReimbursementSearchEmpNo(page.getEmpNo(), pageable);
			}

		}

		final List<Object[]> dataList = data.getContent();
		final ListPageData pageData = new ListPageData();
		pageData.setReimbursementDetails(listConverter.reConvertion(dataList));
		pageData.setPageable(data.getPageable());
		pageData.setNumber(data.getNumber());
		pageData.setNumberOfElements(data.getNumberOfElements());
		pageData.setSize(data.getSize());
		pageData.setTotalElements(data.getTotalElements());
		pageData.setTotalPages(data.getTotalPages());
		return pageData;
	}

	@Override
	public ListPageData searchEmployeeDate(PageViewVo page) {
//		final Pageable pageable = PageRequest.of(page.getPage(), page.getSize(),
//				Sort.by("reimbursement_date").descending());
//		final Page data = reimbursementTrackRepository.findByReimbursementSearchEmpNoDate(page.getDateFrom(),
//				page.getDateTo(), page.getEmpNo(), pageable);

		Page data;
		if (page.getSortKey().equals("reimbursementDate")) {
			page.setSortKey("reimbursement_date");
		} else if (page.getSortKey().equals("totalCost")) {
			page.setSortKey("total_cost");
		} else if (page.getSortKey().equals("empNo")) {
			page.setSortKey("emp_no");
		}

		if (page.getSortKey().isBlank() && page.getSortOrder().isBlank()) {
			final Pageable pageable = PageRequest.of(page.getPage(), page.getSize(),
					Sort.by("reimbursement_date").descending());
			data = reimbursementTrackRepository.findByReimbursementSearchEmpNoDate(page.getDateFrom(), page.getDateTo(),
					page.getEmpNo(), pageable);
		} else {

			if (page.getSortOrder().equals("ascending")) {

				final Pageable pageable = PageRequest.of(page.getPage(), page.getSize(),
						Sort.by(page.getSortKey()).ascending());
				data = reimbursementTrackRepository.findByReimbursementSearchEmpNoDate(page.getDateFrom(),
						page.getDateTo(), page.getEmpNo(), pageable);
			} else {
				final Pageable pageable = PageRequest.of(page.getPage(), page.getSize(),
						Sort.by(page.getSortKey()).descending());
				data = reimbursementTrackRepository.findByReimbursementSearchEmpNoDate(page.getDateFrom(),
						page.getDateTo(), page.getEmpNo(), pageable);
			}

		}

		final List<Object[]> dataList = data.getContent();
		final ListPageData pageData = new ListPageData();
		pageData.setReimbursementDetails(listConverter.reConvertion(dataList));
		pageData.setPageable(data.getPageable());
		pageData.setNumber(data.getNumber());
		pageData.setNumberOfElements(data.getNumberOfElements());
		pageData.setSize(data.getSize());
		pageData.setTotalElements(data.getTotalElements());
		pageData.setTotalPages(data.getTotalPages());
		return pageData;
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED)
	public void sendForApproval(TempVo data) {

		ReimbursementTrack trackID = reimbursementTrackRepository.getOne(data.getReimbursementId());
		List<ReimbursementDetails> bills = reimbursementRepository.findByReimbursementId(data.getReimbursementId());
		trackID.setReimbursementStatus(Status.Pending);
		reimbursementTrackRepository.saveAndFlush(trackID);
		for (int i = 0; i < bills.size(); i++) {
			bills.get(i).setBillStatus(Status.Pending);
			reimbursementRepository.saveAndFlush(bills.get(i));
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteBill(TempVo data) {
		reimbursementRepository.deleteByBillId(data.getBillId());

	}

	@Override
	public void addBill(TempVo data) {
		logger.info("------------------> Add Bill <-----------------------------");
		//ReimbursementTrackVo trackData= reimbursementTrackRepository.getReimbursementId(data.getReimbursementId());
//        logger.info("{}",trackData);
//        System.out.println(trackData);
        
		//ReimbursementDetails bills = reimbursementRepository.findByBillId(data.getBillId());
		

	}

	@Override
	public void updateBill(TempVo data) {
		ReimbursementDetails bill = reimbursementRepository.findByBillId(data.getBillId());
	//	bill.setBillDate(data.getBillDate());
		// bill.setBillNo(data.getBillNo());

	}

}