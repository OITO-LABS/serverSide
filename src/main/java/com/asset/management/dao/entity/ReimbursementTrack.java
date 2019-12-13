
package com.asset.management.dao.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "reimbursement_track")
@ToString
@Getter
@Setter
public class ReimbursementTrack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reimbursement_id")
	private Long reimbursementId;

	@Column(name = "emp_no")
	private String empNo;

	@Column(name = "reimbursement_date")
	private String reimbursementDate;

	@Column(name = "total_cost")
	private double totalCost;
	
	
	@Column(name = "reimbursement_status")
	@Enumerated(EnumType.STRING)
	Status reimbursementStatus;
	

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "reimbursement_id", referencedColumnName = "reimbursement_id")
	private List<ReimbursementDetails> reimbursementDetails;

}

