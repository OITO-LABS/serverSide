package com.asset.management.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asset.management.dao.entity.ProductCategoryEntity;

public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {
	@Query(value = "select * from product_category product where product_category_name=:productCategoryName", nativeQuery = true)
	ProductCategoryEntity findByProductCategoryName(@Param("productCategoryName") String productCategoryName);

}
