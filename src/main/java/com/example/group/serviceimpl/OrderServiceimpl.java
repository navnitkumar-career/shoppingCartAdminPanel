package com.example.group.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.group.Entity.Client;
import com.example.group.Entity.Order;
import com.example.group.Entity.OrderDetail;
import com.example.group.Entity.Product;
import com.example.group.dto.OrderDTO;
import com.example.group.dto.OrderDetailDTO;
import com.example.group.repository.ClientRepository;
import com.example.group.repository.OrderDetailRepository;
import com.example.group.repository.OrderRepository;
import com.example.group.repository.ProductRepository;
import com.example.group.service.OrderService;

@Service
public class OrderServiceimpl implements OrderService {

	public final Logger logger = LoggerFactory.getLogger(CityServiceimpl.class);

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	@Transactional
	public List<OrderDTO> getList() {

		List<Order> order = orderRepository.findAll();
		List<OrderDTO> od = new ArrayList<>();
		for (Order or : order) {
			List<OrderDetail> orderDetailList = orderDetailRepository.getByOrderId(or.getId());    
			List<OrderDetailDTO> orderDetailList1 = orderDetailList.stream().map(t -> {
				OrderDetailDTO odd = new OrderDetailDTO();
				modelMapper.map(t, odd);
				return odd;
			}).collect(Collectors.toList());
			OrderDTO odd = new OrderDTO();
			modelMapper.map(or,odd);
			odd.setOrderDetail(orderDetailList1);
			od.add(odd);
		}
		return od;
	}

	@Override
	@Transactional
	public OrderDTO Add(OrderDTO order) {
		Order or = new Order();
		modelMapper.map(order, or);
		or.setDate(new Date());
		or = orderRepository.save(or);
		modelMapper.map(or, order);
		for (OrderDetailDTO orderDetailDTO : order.getOrderDetail()) {
			OrderDetail od = new OrderDetail();
			modelMapper.map(orderDetailDTO, od);
			od.setOrder(or);
			orderDetailRepository.save(od);
		}
		return order;
	}

	@Override
	@Transactional
	public OrderDTO getId(long id) {
		Order order = orderRepository.findById(id).get();
		OrderDTO od = new OrderDTO();
		List<OrderDetail> orderDetailList = orderDetailRepository.getByOrderId(id);
		List<OrderDetailDTO> orderDetailList1 = orderDetailList.stream().map(t -> {
			OrderDetailDTO odd = new OrderDetailDTO();
			modelMapper.map(t, odd);
			return odd;
		}).collect(Collectors.toList());
		modelMapper.map(order, od);
		od.setOrderDetail(orderDetailList1);
		return od;
	}

//	@Override
//	@Transactional
//	public OrderDTO Update(OrderDTO order) {
//		Order od = orderRepository.findById(order.getId()).get();
//		modelMapper.map(order, od);
//		od = orderRepository.save(od);
//		modelMapper.map(od, order);
//
//		List<OrderDetail> odd = orderDetailRepository.getByOrderId(order.getId());
//		for (OrderDetail odd1 : odd) {
//			orderDetailRepository.deleteById(odd1.getId());
//		}
//		
//		for (OrderDetailDTO orderDetailDTO : order.getOrderDetail()) {
//			OrderDetail odd2 = new OrderDetail();
//			modelMapper.map(orderDetailDTO, odd2);
//			odd2.setOrder(od);
//			orderDetailRepository.save(odd2);
//		}
//
//		return order;
//	}
	@Override
	@Transactional
	public OrderDTO Update(OrderDTO order) {
		Order od = orderRepository.findById(order.getId()).get();
		modelMapper.map(order, od);
		od = orderRepository.save(od);
		modelMapper.map(od, order);

		List<OrderDetail> oldRecord = orderDetailRepository.getByOrderId(order.getId());

		List<OrderDetailDTO> newRecord = order.getOrderDetail();

		for (OrderDetailDTO orderDetailDTO : newRecord) {
			boolean flag = false;
			for (OrderDetail old : oldRecord) {
				if (old.getProduct().getId() == orderDetailDTO.getProductId()) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				OrderDetail odd = new OrderDetail();
				modelMapper.map(orderDetailDTO, odd);
				orderDetailRepository.save(odd);
			}

		}

		for (OrderDetail old : oldRecord) {
			boolean flag = false;
			for (OrderDetailDTO orderDetailDTO : newRecord) {
				if (orderDetailDTO.getProductId() == old.getProduct().getId()) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				orderDetailRepository.deleteById(old.getId());

			}
		}

		for (OrderDetailDTO orderDetailDTO : newRecord) {
			for (OrderDetail old1 : oldRecord) {

				if (old1.getProduct().getId() == orderDetailDTO.getProductId()) {
					orderDetailRepository.save(old1);

				}
			}
		}

		return order;

	}

	@Transactional
	public void Delete(long id) {
		orderRepository.deleteById(id);

		List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(id);

		for (OrderDetail od1 : orderDetailList) {
			orderDetailRepository.deleteById(od1.getId());
		}
	}

//	public long GetBill(long id) {
//
//		List<OrderDetail> orderDetailList = orderDetailRepository.getByOrderId(id);
//		
//		long Result = 0;
//
//		for (OrderDetail odl : orderDetailList) {
//			Result = odl.getProduct().getMrp() * odl.getQuantity() + Result;
//		}
//		return Result;
//
//	}
//	public long GetBill(long id) {
//
//		List<OrderDetail> orderDetailList = orderDetailRepository.getByOrderId(id);
//
//		long Result = 0;
//
//		for (OrderDetail odl : orderDetailList) {
//			Result = odl.getPrice() * odl.getQuantity() + Result;
//		}
//		return Result;
//
//	}

	public Map<String, Object> getBill(long id) {

		List<Map<String, Object>> list = new ArrayList<>();

		List<OrderDetail> orderDetailList = orderDetailRepository.getByOrderId(id);
		float amount = 0;
		float result = 0;
		float discount;

		LinkedHashMap<String, Object> map1 = new LinkedHashMap<>();

		for (OrderDetail odl : orderDetailList) {
			discount = odl.getPrice() * odl.getDiscount() / 100;

			amount = odl.getPrice() - discount;

			result = amount * odl.getQuantity() + result;

			LinkedHashMap<String, Object> map = new LinkedHashMap<>();

			map.put("productName", odl.getProduct().getProduct_name());
			map.put("quantity", odl.getQuantity());
			map.put("price", odl.getPrice());
			map.put("discount", odl.getDiscount());
			map.put("amount", amount);

			list.add(map);

		}
		map1.put("Bill-Amount", result);
		map1.put("Product-Detail", list);
		return map1;
	}

	public Map<String, Object> Get(long id) {

		LinkedHashMap<String, Object> map = new LinkedHashMap<>();

		Order order = orderRepository.findById(id).get();

		map.put("id", order.getId());
		map.put("ClientId", order.getClientId());
		map.put("Date", order.getDate());

		List<Map<String, Object>> list = new ArrayList<>();

		List<OrderDetail> orderDetailList = orderDetailRepository.getByOrderId(id);

		for (OrderDetail orderdetail : orderDetailList) {

			LinkedHashMap<String, Object> map1 = new LinkedHashMap<>();
			map1.put("id", orderdetail.getId());
			map1.put("orderid", orderdetail.getOrder().getId());
			map1.put("productName", orderdetail.getProduct().getProduct_name());
			map1.put("quantity", orderdetail.getQuantity());
			map1.put("price", orderdetail.getPrice());
			map1.put("discount", orderdetail.getDiscount());
			list.add(map1);
		}
		map.put("orderDetail", list);
		return map;
	}

	public List<Map<String, Object>> getList1() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();

		List<Order> order = orderRepository.findAll();

		List<Map<String, Object>> list = new ArrayList<>();

		for (Order or : order) {
			LinkedHashMap<String, Object> map11 = new LinkedHashMap<>();

			List<OrderDetail> orderDetail = orderDetailRepository.getByOrderId(or.getId());
			List<Map<String, Object>> list1 = new ArrayList<>();
			for (OrderDetail ord : orderDetail) {

				LinkedHashMap<String, Object> map1 = new LinkedHashMap<>();
				map1.put("id", ord.getId());
				map1.put("orderId", ord.getOrder().getId());
				map1.put("productName", ord.getProduct().getProduct_name());
				map1.put("quantity", ord.getQuantity());
				map1.put("price", ord.getPrice());
				map1.put("discount", ord.getDiscount());
				list1.add(map1);
			}
			map11.put("id", or.getId());
			map11.put("clientId", or.getClientId());
			map11.put("date", or.getDate());
			map11.put("orderDetail", list1);
			list.add(map11);

			map.put("order", list);

		}

		return list;
	}

	@Transactional
	@Override
	public List<Map<String, Object>> getDetail() {
		List<Map<String, Object>> list = new ArrayList<>();

		List<Client> client = clientRepository.findAll();
		List<Product> productList = productRepository.findAll();

		for (Client cl : client) {
			Map<String, Object> map = new LinkedHashMap<>();

			List<Map<String, Object>> list1 = new ArrayList<>();
			for (Product prod : productList) {

				List<Order> od = orderRepository.findByClientId(cl.getId());
				long count = 0;
				long total = 0;
				long result = 0;
				float discount = 0;
				for (Order odd : od) {

					List<OrderDetail> orderDetail = orderDetailRepository.findByOrderId(odd.getId());

					for (OrderDetail order : orderDetail) {

						if (order.getProduct().getId() == prod.getId()) {

							count = order.getQuantity() + count;

							total = order.getPrice() * count + total;

							discount = (total * order.getProduct().getDiscount()) / 100;

							result = (long) (total - discount);
						}
					}
				}
				if (count > 0) {
					Map<String, Object> map1 = new LinkedHashMap<>();
					map1.put("productName", prod.getProduct_name());
					map1.put("count", count);
					map1.put("totalPrice", total);
					map1.put("discount", discount);
					map1.put("finalBillAmount", result);

					list1.add(map1);
				}
			}
			map.put("clientName", cl.getClientName());
			map.put("productDetail", list1);
			list.add(map);

		}

		return list;
	}

}