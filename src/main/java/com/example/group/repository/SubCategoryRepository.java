package com.example.group.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.group.Entity.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

	List<SubCategory> findByCategoryId(long catId);
}
