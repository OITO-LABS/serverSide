
package com.asset.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asset.management.VO.AssetVO;
import com.asset.management.VO.EmployeeVo;
import com.asset.management.VO.PaginationVO;
import com.asset.management.VO.ProfileVo;
import com.asset.management.VO.ResponseVO;
import com.asset.management.dao.entity.Employee;
import com.asset.management.service.EmployeeService;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	final ResponseVO status = new ResponseVO();

	@GetMapping("listall") // List basic details of all
	public List<EmployeeVo> selectAll() {
		return employeeService.selectAll();
	}

	@GetMapping("/{id}")
	public ProfileVo employeeget(HttpServletResponse response,@PathVariable Long id) {
		ProfileVo detailsObj=new ProfileVo();
		final EmployeeVo obj = employeeService.view(id);
		detailsObj.setEmployeeDetails(obj);
	//	ClassPathResource imgFile = new ClassPathResource("public/photos/"+ id + ".jpg");
		detailsObj.setImagePath("/photos/"+ id + ".jpg");
		return detailsObj;
	}

	@GetMapping("asset/{id}")
	List<AssetVO> getAsset(@PathVariable Long id) {
		return employeeService.getAsset(id);
	}

	@PostMapping("/search")
	public Page<Employee> search(@Valid @RequestBody PaginationVO pagination) {
		return employeeService.searchEmployee(pagination);
	}

	@PostMapping("/list")
	public Page<Employee> page(@Valid @RequestBody PaginationVO pagination) {
		return employeeService.page(pagination);
	}

	@PostMapping
	public ResponseVO register(@Valid @ModelAttribute EmployeeVo obj, Errors error) throws Exception {
		
		try {
			if (error.hasErrors()) {
				status.setStatus("Enter mandatory details in valid format");
			}
			Long id = employeeService.register(obj);
			employeeService.upload(obj.getImage(),id);
			status.setStatus("success");
			status.setMessage("Registration success");

		} catch (final Exception c) {
			status.setStatus("Failed!");
			status.setMessage(c.getMessage());

		}
		return status;
		
	}
	
	@PostMapping("/upload/{id}")
	public ResponseVO upload(@RequestParam ("image") MultipartFile image,@PathVariable Long id) {
		try {
			employeeService.upload(image,id);
			status.setStatus("success");
		} catch (Exception e) {
			status.setStatus("failed");
			status.setMessage(e.getMessage());
		}
		return status;	
	}
	
	@GetMapping("/image/{empId}")
	public void getImage(HttpServletResponse response,@PathVariable Long empId) throws Exception {
		employeeService.viewImage(response,empId);
    }

	
	@DeleteMapping("/{id}")
	public ResponseVO deleteUser(@PathVariable Long id) {// DELETE
		employeeService.delete(id);
		status.setStatus("success");
		return status;
	}

	@GetMapping("/update/{login}")
	public List<String> disableColumns(@PathVariable Long login) {
		return employeeService.disable(login);
	}

	@PutMapping("/{id}") // update all fields
	public ResponseVO update(@PathVariable Long id, @Valid @RequestBody EmployeeVo obj, Errors error) {// PUT
		try {
			if (error.hasErrors()) {
				status.setStatus("Enter mandatory details in valid format");
			}
			employeeService.update(id, obj);
			status.setStatus("success");
		} catch (final Exception c) {
			status.setStatus("Failed!");
			status.setMessage(c.getMessage());
		}
		return status;
	}

	@PutMapping("delete/{id}") // status disable. maintains record
	public ResponseVO delete(@PathVariable String id,@RequestBody EmployeeVo obj) {
		try {
		employeeService.remove(id);
		status.setStatus("success");
		}catch(Exception ex) {
			status.setStatus("Failed!");
			status.setMessage(ex.getMessage());
		}
		return status;
	}
}