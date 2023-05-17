package com.example.group.Contoller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.group.Entity.Category;
import com.example.group.repository.CategoryRepository;

@RestController
@RequestMapping("/category2")
public class RestCategoryController {

	public final Logger logger = LoggerFactory.getLogger(RestCategoryController.class);

	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping(path = "/category-list", produces = "application/json")
	public List<Category> getlist() {
		List<Category> catList = categoryRepository.findAll();
		return catList;
	}

	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public Category add(@RequestBody Category cat) {
		Category cat1 = categoryRepository.save(cat);
		return cat1;
	}

	@GetMapping(path = "/delete/{id}", produces = "application/json")
	public Map<String, String> delet(@PathVariable("id") long id) {
		categoryRepository.deleteById(id);
		HashMap<String, String> msg = new HashMap<>();
		msg.put("status", "success");
		msg.put("message", "Record Deleted");
		return msg;
	}

	@GetMapping(path = "/get/{id}", produces = "application/json")
	public ResponseEntity<Category> get(@PathVariable("id") long id) {
		try {
			Category cat = categoryRepository.findById(id).get();
			return new ResponseEntity<Category>(cat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
	}

	ModelMapper modelmapper = new ModelMapper();

	@PostMapping(path = "/edit", produces = "application/json", consumes = "application/json")
	public Category edit(@RequestBody Category cat) {
		Category cat1 = categoryRepository.findById(cat.getId()).get();
		modelmapper.map(cat, cat1);
		Category cat2 = categoryRepository.save(cat1);
		return cat2;
	}
}
