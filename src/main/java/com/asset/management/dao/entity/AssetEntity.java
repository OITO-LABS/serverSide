
package com.asset.management.dao.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "asset_details")
@Getter
@Setter
@ToString
public class AssetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "asset_id")
	private Long assetId;

	@Column(name = "asset_key")
	private String assetKey;

	@Column(name = "model")
	private String model;

	@Column(name = "mac_id")
	private String macId;

	@Column(name = "sim_no")
	private String simNo;

	@Column(name = "msisdn_no")
	private String msisdnNo;

	@Column(name = "enable_status")
	@Enumerated(EnumType.STRING)
	private Status enableStatus;

	@Column(name = "product_category_name")
	private String productCategory;

	@OneToMany(mappedBy = "")
	private Set<AssetAssignEntity> assetAssign;

	@Column(name = "updated_id")
	private String updatedId;

	@Column(name = "add_date")
	@Temporal(TemporalType.TIMESTAMP)
	Date addDate;

	@Column(name = "updated_time")
	@Temporal(TemporalType.TIMESTAMP)
	Date updatedDate;
	@Column(name = "employee_details")
	Long employeeId;
}

