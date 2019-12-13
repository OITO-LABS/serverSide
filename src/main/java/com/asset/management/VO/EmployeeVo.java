
package com.asset.management.VO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.asset.management.dao.entity.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeVo {

	private Long empId;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String email;
	private String designation;
	@NotNull
	@Size(max = 10)
	private String dob;
	@NotBlank
	@Size(min = 10)
	private String contactNo;
	@NotBlank
	private String emergencyContactName;
	@NotBlank
	@Size(min = 6, max = 15)
	private String emergencyContact;
	private String healthCardNo;
	private String bloodGroup;
	private Status status;
	private String empNo;
	private String joiningDate;
	private String terminationDate;
	MultipartFile image;
}


