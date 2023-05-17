package com.example.group.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.group.Entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findBySubcategory(long subCatId);
	
	List<Product> findByQuantityGreaterThan(long quantity);

	@Query(value = "SELECT * FROM product u WHERE u.subcategory = ?1 order by productid", nativeQuery = true)
	List<Product> getBySubcategory(String subCatId);
	

	
}
