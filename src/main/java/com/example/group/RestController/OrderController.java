package com.example.group.RestController;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.group.dto.OrderDTO;
import com.example.group.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	public final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	OrderService orderService;

	@GetMapping(path = "/get-list", produces = "application/json")
	public List<OrderDTO> getlist() {

		List<OrderDTO> orderlist = orderService.getList();

		return orderlist;
	}

	@GetMapping(path = "/get-list1", produces = "application/json")
	public List<Map<String, Object>> getList() {
		List<Map<String, Object>> list = orderService.getList1();
		return list;
	}

	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public OrderDTO addSave(@RequestBody OrderDTO order) {
		OrderDTO order1 = orderService.Add(order);
		return order1;
	}

	@GetMapping(path = "/get/{id}", produces = "application/json")
	public OrderDTO getId(@PathVariable("id") long id) {
		OrderDTO order = orderService.getId(id);

		return order;
	}

	@GetMapping(path = "/get1/{id}", produces = "application/json")
	public Map<String, Object> Get(@PathVariable("id") long id) {
		Map<String, Object> order = orderService.Get(id);

		return order;
	}

	@GetMapping(path = "/delete/{id}", produces = "application/json")
	public void delete(@PathVariable("id") long id) {
		orderService.Delete(id);
	}

	@PostMapping(path = "/edit", consumes = "application/json", produces = "application/json")
	public OrderDTO edit(@RequestBody OrderDTO order) {
		OrderDTO order1 = orderService.Update(order);
		return order1;
	}

	@GetMapping(path = "/getbill/{id}", produces = "application/json")
	public Map<String, Object> getBill(@PathVariable("id") long id) {

		Map<String, Object> order = orderService.getBill(id);

		return order;
	}
	
	
	@GetMapping(path = "/get-detail", produces = "application/json")
	public List<Map<String, Object>> getDetail() {
		List<Map<String, Object>> list = orderService.getDetail();
		return list;
	}

}
