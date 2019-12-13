package com.asset.management.dao.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.asset.management.dao.entity.ReimbursementDetails;
import com.asset.management.dao.entity.Status;

public interface ReimbursementRepository extends JpaRepository<ReimbursementDetails, Long> {

	@Query(value = "select * from reimbursement_details", nativeQuery = true)
	List<ReimbursementDetails> selectReimbursementDetails();
	
	@Query(value = "select * from reimbursement_details dtls where dtls.reimbursement_id = :reimbursementId", nativeQuery = true)
	List<ReimbursementDetails> findByReimbursementId(@Param("reimbursementId") Long reimbursementId);

	@Query(value = "SELECT * FROM reimbursement_details u WHERE u.reimbursement_id = :reimbursementId", nativeQuery = true)
	List<Object[]> findByReimbursementTrack(@Param("reimbursementId") Long reimbursementId);

	@Query(value = "select r.* from reimbursement_details r where r.reimbursement_id in(select t.reimbursement_id from reimbursement_track t where t.emp_no=:empNo)", nativeQuery = true)
	List<Object[]> findByReimbursementEmpNo(@Param("empNo") String empNo);

	@Query(value = "SELECT * FROM reimbursement_details u WHERE u.reimbursement_bill_no = :billNo", nativeQuery = true)
	ReimbursementDetails findByReimbursementBillNo(@Param("billNo") BigInteger billNo);

	
	@Modifying(flushAutomatically = true)
	@Query(value = "UPDATE reimbursement_details dtls SET dtls.bill_status=:status WHERE dtls.reimbursement_id=:reimbursementId", nativeQuery = true)
	void setApprovalStatus(@Param("reimbursementId") Long reimbursementId,@Param("status") String status);

	@Modifying(flushAutomatically = true)
	@Query(value = "DELETE FROM reimbursement_details u WHERE u.track_id =:billId", nativeQuery = true)
	void deleteByBillId(@Param("billId") Long billId);

	
	@Query(value = "select * from reimbursement_details dtls where dtls.track_id = :billId", nativeQuery = true)
	ReimbursementDetails findByBillId(@Param("billId") Long billId);

	
	
	
}



