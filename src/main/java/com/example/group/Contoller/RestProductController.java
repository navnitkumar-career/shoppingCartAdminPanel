package com.example.group.Contoller;

import java.util.HashMap;
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

import com.example.group.dto.ProductDTO;
import com.example.group.service.ProductService;

@RestController
@RequestMapping("/product2")
public class RestProductController {

	public final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;

	@GetMapping(path = "/get-list", produces = "application/json")
	public List<ProductDTO> getList() {
		List<ProductDTO> prodList = productService.getList();
		return prodList;
	}

	@GetMapping(path = "/get-list-bysubcat/{id}", produces = "application/json")
	public List<ProductDTO> getListBySubCat(@PathVariable("id") int subCatId) {
		List<ProductDTO> prodList = productService.getListBySubCat(subCatId);
		return prodList;
	}

	@GetMapping(path = "/get-list-byquantity/{id}", produces = "application/json")
	public List<ProductDTO> getListByQuantity(@PathVariable("id") int quantity) {
		List<ProductDTO> prodList = productService.getListByQuantity(quantity);
		return prodList;
	}

	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public ProductDTO addsave(@RequestBody ProductDTO prod) {

		ProductDTO prod2 = productService.add(prod);

		return prod2;
	}

	@GetMapping(path = "/get/{id}", produces = "application/json")
	public ProductDTO get(@PathVariable("id") int id) {
		ProductDTO prod = productService.get(id);
		return prod;

//		try {
//			ProductDTO product = productService.get(id);	
//			return new ResponseEntity<>(pro, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
	}

	@PostMapping(path = "/edit", consumes = "application/json", produces = "application/json")
	public ProductDTO editsave(@RequestBody ProductDTO prod2) {

		ProductDTO prod1 = productService.update(prod2);
		return prod1;
	}

	@GetMapping(path = "/delete/{id}", produces = "application/json")
	public void delete(@PathVariable("id") int id) {
		productService.delete(id);
		Map<String, String> out = new HashMap<>();
		out.put("status", "Success");
		out.put("message", "Record deleted");

	}

}
