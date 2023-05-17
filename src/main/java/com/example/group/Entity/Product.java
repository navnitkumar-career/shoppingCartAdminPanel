package com.example.group.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@Column(name = "subcategoryid")
	long subcategory;
	
	String product_name;
	long mrp;
	long quantity;
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

	public long getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(long subcategory) {
		this.subcategory = subcategory;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public long getMrp() {
		return mrp;
	}

	public void setMrp(long mrp) {
		this.mrp = mrp;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", subcategory=" + subcategory + ", product_name=" + product_name + ", mrp=" + mrp
				+ ", quantity=" + quantity + ", discount=" + discount + "]";
	}

}
