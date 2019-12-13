
package com.asset.management.dao;

import java.util.Calendar;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asset.management.VO.AssetAssignVO;
import com.asset.management.VO.ListPageData;
import com.asset.management.VO.PageViewVo;
import com.asset.management.VO.ResponseVO;
import com.asset.management.VO.mapping.AssetAssignMapper;
import com.asset.management.dao.entity.AssetAssignEntity;
import com.asset.management.dao.entity.AssetEntity;
import com.asset.management.dao.entity.Employee;
import com.asset.management.dao.entity.Status;
import com.asset.management.dao.repository.AssetAssignRepository;
import com.asset.management.dao.repository.AssetRepository;
import com.asset.management.dao.repository.EmployeeRepository;

import lombok.ToString;

@ToString
@Component
public class AssetAssignDaoImpl implements AssetAssignDao {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AssetDao.class);
	@Autowired
	AssetAssignMapper map;

	@Autowired
	AssetRepository assetRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	AssetAssignRepository assetAssign;

	// Assign asset to an employee.
	@Override
	public ResponseVO assetAssigned(Long assetId, AssetAssignVO assetAssignVO) {
		final ResponseVO response = new ResponseVO();
		final AssetEntity asset = assetRepository.findByAssetId(assetId);
		final AssetAssignEntity assetAssignEntity = map.assetAssignConvertion(assetAssignVO);
		final Employee emp = assetAssignEntity.getEmployee();
		final Employee employee = employeeRepository.findByEmpNo(emp.getEmpNo());
		System.out.println(employee.getEmpId());
		if (asset != null && asset.getEnableStatus().equals(Status.Unassigned)) {
			final Calendar cal = Calendar.getInstance();
			asset.setEnableStatus(Status.Assigned);
			asset.setUpdatedDate(cal.getTime());
			asset.setEmployeeId(employee.getEmpId());
			assetRepository.flush();
			assetAssignEntity.setAssetAssign(asset);
			assetAssignEntity.setEmployee(employee);
			assetAssignEntity.setAssignStatus(Status.Assigned);
			assetAssignEntity.setAddDate(cal.getTime());
			assetAssignEntity.setUpdatedDate(cal.getTime());
			if (assetAssign.saveAndFlush(assetAssignEntity).getId() != null) {
				response.setStatus("success");
				return response;
			}
		}
		response.setStatus("failed");
		return response;
	}

	// Return a device
	@Override
	public ResponseVO returnDevice(Long assetId, AssetAssignVO assetAssignVO) {
		final ResponseVO response = new ResponseVO();
		final AssetEntity asset = assetRepository.findByAssetId(assetId);
		final AssetAssignEntity assetAssignEntity = assetAssign.findByAssetAssign(asset.getAssetId());
		if (asset != null && assetAssignEntity != null) {
			final Calendar cal = Calendar.getInstance();
			asset.setEnableStatus(Status.Unassigned);
			asset.setUpdatedDate(cal.getTime());
			assetAssignEntity.setAssignStatus(Status.Returned);
			assetAssignEntity.setReturnDate(assetAssignVO.getReturnDate());
			assetAssignEntity.setCause(assetAssignVO.getCause());
			assetAssignEntity.setUpdatedId(assetAssignVO.getUpdatedId());
			assetAssignEntity.setUpdatedDate(cal.getTime());
			assetRepository.flush();
			if (assetAssign.saveAndFlush(assetAssignEntity).getId() != null) {
				response.setStatus("success");
				return response;
			}
		}
		response.setStatus("failed");
		return response;
	}

	// Fetch all the asset details.
	@Override
	public List<AssetAssignVO> getAll(Long empId) {
		final List<AssetAssignEntity> assetAssignEntity = assetAssign.findByEmployee(empId);
		return map.assetAssignReConvertion1(assetAssignEntity);
	}

	@Override
	public ListPageData searchEmployeeDate(PageViewVo page) {
		return null;
	}

}
