
package com.asset.management.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;

import com.asset.management.VO.AssetVO;
import com.asset.management.VO.EmployeeVo;
import com.asset.management.VO.Mail;
import com.asset.management.VO.PaginationVO;
import com.asset.management.VO.mapping.AssetMapperInterface;
import com.asset.management.VO.mapping.EmployeeMapping;
import com.asset.management.dao.entity.Employee;
import com.asset.management.dao.entity.Status;
import com.asset.management.dao.repository.AssetRepository;
import com.asset.management.dao.repository.EmployeeRepository;
import com.asset.management.dao.repository.LoginRepository;
import com.asset.management.service.LoginService;

@Component
public class EmployeeDaoImpl implements EmployeeDao {
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	private AssetRepository AssetRepository;

	@Autowired
	private EmployeeMapping mappingObj;

	@Autowired
	private AssetMapperInterface map;

	@Autowired
	private LoginDao loginDao;

	@Autowired
	private LoginService loginService;

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public List<EmployeeVo> selectAll() {
		final List<Employee> employee = employeeRepository.getEmpNo();
		return mappingObj.employeeListConvert(employee);
	}

	@Override
	public Long register(EmployeeVo employee) throws Exception {// POST
		final Employee email = employeeRepository.findByEmail(employee.getEmail());
		final Employee contact = employeeRepository.findByContactNo(employee.getContactNo());
		final Employee empNo = employeeRepository.findByEmpNo(employee.getEmpNo());
		final Employee healthCardNo = employeeRepository.findByHealthCardNo(employee.getHealthCardNo());
		final Employee emp = mappingObj.Map(employee);
		if (email == null) {
			if (contact == null) {
				if (empNo == null) {
					if (healthCardNo == null) {
						emp.setStatus(Status.Active);
						employeeRepository.save(emp);

					} else {
						throw new Exception("Health Card no already exists!");
					}
				} else {
					throw new Exception("Employee no already exists!");
				}
			} else {
				throw new Exception("Contact no already exists!");
			}
		} else {
			throw new Exception("Email already exists!");
		}
		loginDao.create(emp);

		Long id = (emp.getEmpId());
		try {
		Mail obj = new Mail();
		obj.setTo(emp.getEmail());
		obj.setToken(loginService.generatePasswordToken(id));
		System.out.println("Mail sending to : "+ obj.getTo());
		loginService.sendmail(obj);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return emp.getEmpId();
	}

	@Override
	public EmployeeVo view(Long id) {
		Employee emp = null;
		final java.util.Optional<Employee> temp = employeeRepository.findById(id);
		if (temp.isPresent()) {
			emp = temp.get();
		}
		return mappingObj.employeeMap(emp);
	}

	@Override
	public void delete(Long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED)
	public synchronized void update(Long empId, EmployeeVo obj) {
		final java.util.Optional<Employee> temp = employeeRepository.findById(empId);
		if (temp.isPresent()) {
			final Employee emp = mappingObj.Map(obj);
			employeeRepository.update(empId, emp.getFirstName(), emp.getLastName(), emp.getDesignation(),
					emp.getEmail(), emp.getContactNo(), emp.getDob(), emp.getEmergencyContactName(),
					emp.getEmergencyContact(), emp.getHealthCardNo(), emp.getBloodGroup(), emp.getEmpNo());
			loginRepository.update(empId, emp.getEmail());
		}
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED)
	public void remove(String empNo) throws Exception {
		final Employee emp = employeeRepository.findByEmpNo(empNo);
		List<AssetVO> asset = getAsset(emp.getEmpId());
		if (asset.isEmpty()) {
			emp.setStatus(Status.Terminated);
			employeeRepository.flush();
			loginRepository.deleteByEmployeeId(emp.getEmpId());
		} else {
			throw new Exception("Return assets before employee resign");
		}

	}

	@Override
	public Page<Employee> page(PaginationVO pagination) {// searchkey==null
		
		if(pagination.getSortKey().equals("empNo")) {
			pagination.setSortKey("emp_no");
		}else if(pagination.getSortKey().equals("firstName")) {
			pagination.setSortKey("emp_fname");
		}else if(pagination.getSortKey().equals("contactNo")) {
			pagination.setSortKey("mobile_no");
		}
		Pageable pageable;

		if ((pagination.getSortKey()).equals("")) {
			pageable = PageRequest.of(pagination.getPage(), pagination.getLimit());
			return employeeRepository.findE(pageable);
						
		}		
		else {// empNo,firstName,email,contactNo
			if(pagination.getSortOrder().equals("ascending")) {
			pageable = PageRequest.of(pagination.getPage(), pagination.getLimit(),Sort.by(pagination.getSortKey()).ascending());		
			return employeeRepository.findE(pageable);
		}else {
			pageable = PageRequest.of(pagination.getPage(), pagination.getLimit(),Sort.by(pagination.getSortKey()).descending());		
			return employeeRepository.findE(pageable);
		}
		}
	}

	@Override
	public Page<Employee> searchEmployee(PaginationVO pagination) {
		if(pagination.getSortKey().equals("empNo")) {
			pagination.setSortKey("emp_no");
		}else if(pagination.getSortKey().equals("firstName")) {
			pagination.setSortKey("emp_fname");
		}else if(pagination.getSortKey().equals("contactNo")) {
			pagination.setSortKey("mobile_no");
		}
		Pageable pageable;
		if ((pagination.getSortKey()).equals("")) {
			pageable = PageRequest.of(pagination.getPage(), pagination.getLimit());
			return employeeRepository.searchEmployee(pagination.getSearchkey(), pageable);						
		}		
		else {// empNo,firstName,email,contactNo
			if(pagination.getSortOrder().equals("ascending")) {
			pageable = PageRequest.of(pagination.getPage(), pagination.getLimit(),Sort.by(pagination.getSortKey()).ascending());		
			return employeeRepository.searchEmployee(pagination.getSearchkey(), pageable);
		}else {
			pageable = PageRequest.of(pagination.getPage(), pagination.getLimit(),Sort.by(pagination.getSortKey()).descending());		
			return employeeRepository.searchEmployee(pagination.getSearchkey(), pageable);
		}
		}	
		}
	
	@Override
	public List<AssetVO> getAsset(Long id) {
		final String enableStatus = (String.valueOf((Status.Assigned).name()));
		return map.assetReConvertion(AssetRepository.findByEmployee(id, enableStatus));
	}

	@Override
	public List<String> disable(Long login) {// disable lists for update
		if (login == 0) {// admin
			return new ArrayList<>(List.of("empNo"));
		}
		return new ArrayList<>(List.of("empNo", "designation", "healthCardNo"));
	}

}

