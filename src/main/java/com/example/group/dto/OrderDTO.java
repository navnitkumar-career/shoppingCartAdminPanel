package com.example.group.dto;

import java.util.Date;
import java.util.List;

public class OrderDTO {

	long id;
	Date date;
	long clientId;

	List<OrderDetailDTO> orderDetail;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public List<OrderDetailDTO> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetailDTO> orderDetail) {
		this.orderDetail = orderDetail;
	}

	@Override
	public String toString() {
		return "OrderDTO [id=" + id + ", date=" + date + ", clientId=" + clientId + ", orderDetail=" + orderDetail
				+ "]";
	}

}
