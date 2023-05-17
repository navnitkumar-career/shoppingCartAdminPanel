package com.example.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.group.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
