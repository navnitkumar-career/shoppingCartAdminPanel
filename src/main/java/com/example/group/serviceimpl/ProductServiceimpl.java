package com.example.group.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.group.Entity.Product;
import com.example.group.dto.ProductDTO;
import com.example.group.repository.ProductRepository;
import com.example.group.service.ProductService;

@Service
public class ProductServiceimpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ModelMapper modelMapper;

	public ProductDTO add(ProductDTO prod) {
		Product p = new Product();
		modelMapper.map(prod, p);
		p = productRepository.save(p);
		modelMapper.map(p, prod);
		return prod;
	}

	public ProductDTO update(ProductDTO prod) {
		Product p = productRepository.findById(prod.getId()).get();
		modelMapper.map(prod, p);
		productRepository.save(p);
		modelMapper.map(p, prod);
		return prod;
	}

	public void delete(int id) {
		productRepository.deleteById((long) id);
	}

	public List<ProductDTO> getList() {
		List<Product> prodList = productRepository.findAll();
		List<ProductDTO> prodList1 = prodList.stream().map(t -> {
			ProductDTO p = new ProductDTO();
			modelMapper.map(t, p);
			return p;
		}).collect(Collectors.toList());
		return prodList1;
	}

	public List<ProductDTO> getListBySubCat(int subCatId) {
		List<Product> prodList = productRepository.findBySubcategory(subCatId);
		List<ProductDTO> prodList1 = prodList.stream().map(t -> {
			ProductDTO p = new ProductDTO();
			modelMapper.map(t, p);
			return p;
		}).collect(Collectors.toList());
		return prodList1;
	}

	public List<ProductDTO> getListByQuantity(int quantity) {
		List<Product> prodList = productRepository.findByQuantityGreaterThan(quantity);
		List<ProductDTO> prodList1 = prodList.stream().map(t -> {
			ProductDTO p = new ProductDTO();
			modelMapper.map(t, p);
			return p;
		}).collect(Collectors.toList());
		return prodList1;
	}

	public ProductDTO get(int id) {
		Product product = productRepository.findById((long) id).get();
		ProductDTO p = new ProductDTO();
		modelMapper.map(product, p);
		return p;
	}
}
