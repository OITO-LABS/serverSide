

package com.asset.management.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee_details")
@Getter
@Setter
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Long empId;
	@Column(name = "emp_fname")
	private String firstName;
	@Column(name = "emp_lname")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "designation")
	private String designation;
	@Column(name = "date_of_birth")
	private String dob;
	@Column(name = "mobile_no")
	private String contactNo;
	@Column(name = "emergency_contact_name")
	private String emergencyContactName;
	@Column(name = "emergency_contact_no")
	private String emergencyContact;
	@Column(name = "health_card_no")
	private String healthCardNo;
	@Column(name = "blood_group")
	private String bloodGroup;
	@Column(name = "enable_status")
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column(name = "emp_no")
	private String empNo;
	@Column(name ="joining_date")
	private String joiningDate;
	@Column(name ="termination_date")
	private String terminationDate;
	
}




