
package com.asset.management.dao.entity;

import java.util.Date;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "device_assignment")
@Getter
@Setter
@ToString
public class AssetAssignEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "asset_id")
	private AssetEntity assetAssign;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "emp_id")
	private Employee employee;

	@Column(name = "issue_date")
	private String issueDate;

	@Column(name = "return_date")
	private Date returnDate;

	@Column(name = "cause")
	private String cause;

	@Column(name = "assign_status")
	@Enumerated(EnumType.STRING)
	private Status assignStatus;

	@Column(name = "updated_id")
	private Long updatedId;

	@Column(name = "add_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date addDate;

	@Column(name = "updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
}

