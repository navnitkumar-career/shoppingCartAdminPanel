package com.example.group.dto;

public class OrderDetailDTO {

	long id;
	long orderId;
	long productId;
	long quantity;
	long price;
	float discount;

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

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
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
		return "OrderDetailDTO [id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", quantity="
				+ quantity + ", price=" + price + ", discount=" + discount + "]";
	}
}
