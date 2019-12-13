


package com.asset.management.dao.entity;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "reimbursement_details")
@ToString
@Getter
@Setter
public class ReimbursementDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "track_id")
	private Long trackId;

	//@ManyToOne(cascade = CascadeType.ALL) @JoinColumn
	@Column(name = "reimbursement_id")
	private Long reimbursementTrack;

	@Column(name = "reimbursement_bill_date")
	private String billDate;

	@Column(name = "reimbursement_description")
	private String reimbursementDescription;

	@Column(name = "reimbursement_bill_no")
	private BigInteger billNo;

	@Column(name = "reimbursement_cost")
	private double cost;

	@Column(name = "category_name")
	private String categoryName;
	
	@Column(name = "bill_status")
	@Enumerated(EnumType.STRING)
	private Status billStatus;
}


