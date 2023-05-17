package com.example.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.group.Entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
