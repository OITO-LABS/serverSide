
package com.asset.management.dao.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.asset.management.dao.entity.ReimbursementTrack;
import com.asset.management.dao.entity.Status;

public interface ReimbursementTrackRepository extends JpaRepository<ReimbursementTrack, Long> {

	ReimbursementTrack findByReimbursementId(@Param("reimbursementId") Long reimbursementId);

	@Query(value = "select * from reimbursement_track", nativeQuery = true)
	List<ReimbursementTrack> getReimbursementId();

	@Query(value = "select * from reimbursement_track t where t.reimbursement_date BETWEEN :startDate AND :endDate", nativeQuery = true)
	Page getAllBetweenDates(Pageable page, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query(value = "select * from reimbursement_track t where emp_no=:empNo", nativeQuery = true)
	Page findByReimbursementEmpNo(Pageable page, @Param("empNo") String empNo);

	@Query(value = "select * from reimbursement_track", nativeQuery = true)
	Page getReimbursementView(Pageable page);

	@Query(value = "select * from reimbursement_track re where emp_no like %?1% ", nativeQuery = true)
	Page findByReimbursementSearchEmpNo(String searchkey, Pageable pageable);

	@Query(value = "select * from reimbursement_track t where t.reimbursement_date BETWEEN :startDate AND :endDate AND t.emp_no=:empNo", nativeQuery = true)
	Page findByReimbursementSearchEmpNoDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
			@Param("empNo") String empNo, Pageable pageable);

	
	
	@Modifying(flushAutomatically = true)
	@Query(value = "UPDATE reimbursement_track t SET t.reimbursement_status=:status WHERE t.reimbursement_id=:reimbursementId", nativeQuery = true)
	void setApprovalStatus(@Param("reimbursementId")Long reimbursementId, @Param("status") String status);

}
