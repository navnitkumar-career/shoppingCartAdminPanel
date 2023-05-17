package com.example.group.service;

import java.util.List;

import com.example.group.dto.ProductDTO;

public interface ProductService {

	ProductDTO add(ProductDTO prod);

	ProductDTO update(ProductDTO prod);

	void delete(int id);

	ProductDTO get(int id);

	List<ProductDTO> getList();
	
	List<ProductDTO> getListBySubCat(int subCatId);
	
	List<ProductDTO> getListByQuantity(int quantity);
}
