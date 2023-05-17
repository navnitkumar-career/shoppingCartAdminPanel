package com.example.group.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.group.Entity.City;

public interface CityRepository extends JpaRepository<City, Long> {

	
	List<City> findByStateId(long stateId);
}
