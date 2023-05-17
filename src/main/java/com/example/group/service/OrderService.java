package com.example.group.service;

import java.util.List;
import java.util.Map;

import com.example.group.dto.OrderDTO;

public interface OrderService {

	List<OrderDTO> getList();

	OrderDTO Add(OrderDTO order);

	OrderDTO Update(OrderDTO order);

	void Delete(long id);

	OrderDTO getId(long id);

	Map<String, Object> Get(long id);

	Map<String, Object> getBill(long id);

	List<Map<String, Object>> getList1();

	List<Map<String, Object>> getDetail();

	// List<OrderDTO> getListByState(int state);

}
