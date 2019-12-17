
package com.asset.management.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asset.management.dao.entity.AssetAssignEntity;

public interface AssetAssignRepository extends JpaRepository<AssetAssignEntity, Long> {
	@Query(value = "select * from device_assignment asset where asset_id=:assetAssign and assign_status!='Returned'", nativeQuery = true)
	AssetAssignEntity findByAssetAssign(@Param("assetAssign") Long assetAssign);

	@Override
	Optional<AssetAssignEntity> findById(Long EmpId);

	@Query(value = "select * from device_assignment asset where emp_id=:employee", nativeQuery = true)
	List<AssetAssignEntity> findByEmployee(@Param("employee") Long employee);

	@Query(value = "select ad.asset_id as assetId,ad.asset_key as assetKey,ad.enable_status as Status,ad.model,e.emp_id as empId,e.emp_no as empNo from asset_details ad left Join device_assignment ds on ad.asset_id = ds.asset_id left join employee_details e on e.emp_id = ds.emp_id where ad.enable_status !=2", nativeQuery = true)
	List<Object[]> findSelectedFields();

	@Query(value = "select ad.asset_id as assetId,ad.asset_key as assetKey,ad.product_category_name as productCategoryName,ad.enable_status as Status,ad.model,e.emp_id as empId,e.emp_no as empNo "
			+ "		from asset_details ad "
			+ "		left Join device_assignment ds on ad.asset_id=ds.asset_id and ad.enable_status = ds.assign_status "
			+ "		left join employee_details e on e.emp_id = ds.emp_id "
			+ "where ad.enable_status !='Disabled' and ad.enable_status!='Inactive' and ad.product_category_name like %?1% or asset_key like %?1% or emp_fname like %?1%", nativeQuery = true, countQuery = "select count(ad.asset_id) as count from asset_details ad left Join device_assignment ds on ad.asset_id = ds.asset_id and ad.enable_status=ds.assign_status left join employee_details e on e.emp_id = ds.emp_id where ad.enable_status!='Disabled' and ad.enable_status!='Inactive' and ad.product_category_name like %?1% or asset_key like %?1% or emp_fname like %?1%")
	Page findSelectedField(String productName, @Param("pageable") Pageable pageable);
	
	

	@Query(value = "select ad.asset_id as assetId,ad.asset_key as assetKey,ad.product_category_name as productCategoryName,ad.enable_status as Status,ad.model,e.emp_id as empId,e.emp_no as empNo \n" + 
			"from asset_details ad " + 
			"	left Join device_assignment ds on ad.asset_id=ds.asset_id and ad.enable_status = ds.assign_status \n" + 
			"	left join employee_details e on e.emp_id = ds.emp_id " + 
			"where ad.enable_status !='Disabled' and  ad.enable_status !='Inactive'", nativeQuery = true, countQuery = "select count(ad.asset_id) as count from asset_details ad left Join device_assignment ds on ad.asset_id = ds.asset_id and ad.enable_status=ds.assign_status left join employee_details e on e.emp_id = ds.emp_id where ad.enable_status!='Disabled'")
	Page findAssetDetails(@Param("pageable") Pageable pageable);
	
	

	@Query(value = ""
			+ "select ad.asset_id as assetId,e.emp_id as empId,e.emp_no as empNo,e.emp_fname as fname,e.emp_lname  as lname,ds.issue_date as issueDate,ds.return_date as returnDate,ds.cause as cause "
			+ "		from asset_details ad " + "		left join device_assignment ds on ad.asset_id=ds.asset_id "
			+ "		left join employee_details e on ds.emp_id=e.emp_id "
			+ "where ad.asset_id=:assetId", nativeQuery = true)
	List<Object[]> findAssetHistory(@Param("assetId") Long assetId);
	
	
	@Query(value="select ad.asset_id as assetId,ad.asset_key as assetKey,ad.product_category_name as productCategoryName,ad.enable_status as Status,ad.model,e.emp_id as empId,e.emp_no as empNo \n" + 
			"from asset_details ad " + 
			"	left Join device_assignment ds on ad.asset_id=ds.asset_id and ad.enable_status = ds.assign_status " + 
			"	left join employee_details e on e.emp_id = ds.emp_id " + 
			"where ad.enable_status ='Disabled' or  ad.enable_status='Inactive'",nativeQuery=true, countQuery="select count(ad.asset_id) as count from asset_details ad left Join device_assignment ds on ad.asset_id = ds.asset_id and ad.enable_status=ds.assign_status left join employee_details e on e.emp_id = ds.emp_id where ad.enable_status='Disabled' or ad.enable_status='Inactive'")
	Page findInactiveAssets(@Param("pageable") Pageable pageable);

}
