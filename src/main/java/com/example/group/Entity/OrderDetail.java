package com.example.group.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderDetail {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	long id;

	@ManyToOne
	Product product;

	@ManyToOne
	Order order;

	// long orderId;

	long quantity;
	long price;
	float discount;

//	public long getOrderId() {
//		return orderId;
//	}
//
//	public void setOrderId(long orderId) {
//		this.orderId = orderId;
//	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", product=" + product + ", order=" + order + ", quantity=" + quantity
				+ ", price=" + price + ", discount=" + discount + "]";
	}

}
