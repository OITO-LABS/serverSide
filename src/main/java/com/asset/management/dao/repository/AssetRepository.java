
package com.asset.management.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.asset.management.dao.entity.AssetEntity;

public interface AssetRepository
		extends JpaRepository<AssetEntity, Long>, PagingAndSortingRepository<AssetEntity, Long> {
	@Query(value = "select * from asset_details asset where asset_key=:assetKey", nativeQuery = true)
	AssetEntity findByAssetKey(@Param("assetKey") String assetKey);

	@Query(value = "SELECT asset.asset_key as assetKey,asset.model as model,asset.enable_status as enableStatus FROM asset_details asset", nativeQuery = true)
	List<Object> findSelectedField();

	@Query(value = "select * from asset_details asset where asset_id=:assetId", nativeQuery = true)
	AssetEntity findByAssetId(@Param("assetId") Long assetId);

	@Query(value = "select ad.asset_id as assetId,ad.asset_key as assetKey,ad.product_category_name as productCategoryName,ad.mac_id as macId,ad.sim_no as simNo,ad.msisdn_no as msisdnNo,ad.enable_status as Status,ad.model as model,e.emp_id as empId,e.emp_no as empNo,e.emp_fname as fname,e.emp_lname as lname,ds.issue_date as issueDate,ds.return_date as returnDate,ds.cause as cause "
			+ "	from asset_details ad "
			+ "	left Join device_assignment ds on ad.asset_id=ds.asset_id and ad.enable_status = ds.assign_status "
			+ "	left join employee_details e on e.emp_id = ds.emp_id "
			+ "where ad.asset_id =:assetId", nativeQuery = true)
	List<Object[]> getAssetDetails(@Param("assetId") Long assetId);

	
	@Query(value = "select * from asset_details where employee_details=:id and enable_status=:enableStatus", nativeQuery = true)
	List<AssetEntity> findByEmployee(@Param("id") Long id, @Param("enableStatus") String enableStatus);
}
