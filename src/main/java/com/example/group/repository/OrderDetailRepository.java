package com.example.group.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.group.Entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

	@Query("SELECT od from OrderDetail od where od.order.id =:orderId ")
	List<OrderDetail> findByOrderId(long orderId);

	List<OrderDetail> getByOrderId(long orderId);

	
}
