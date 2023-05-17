package com.example.group.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.group.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	//@Query("SELECT od from Order od where od.clientId.id =:clientId ")
	List<Order> findByClientId(long clientId);

}
