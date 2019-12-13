package com.asset.management.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asset.management.dao.entity.CategoryDetails;

public interface CategoryRepository extends JpaRepository<CategoryDetails, Long> {

	@Query(value = "select * from category  where category_name=:categoryName", nativeQuery = true)
	CategoryDetails findByCategoryName(@Param("categoryName") String categoryName);

	@Query(value = "select * from category", nativeQuery = true)
	List<CategoryDetails> selectCategory();

}