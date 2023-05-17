package com.example.group.Contoller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.group.Entity.SubCategory;
import com.example.group.repository.SubCategoryRepository;

@RestController
@RequestMapping("/subcategory1")
public class RestSubCategoryController {

	@Autowired
	SubCategoryRepository subCategoryRepository;

	@GetMapping(path = "/get-list", produces = "application/json")
	public List<SubCategory> getlist() {
		List<SubCategory> subcat = subCategoryRepository.findAll();
		return subcat;
	}

	@PostMapping(path = "/add", produces = "application/json", consumes = "application/json")
	public SubCategory add(@RequestBody SubCategory sub) {
		SubCategory subcat = subCategoryRepository.save(sub);
		return subcat;
	}

	@GetMapping(path = "get/{id}", produces = "application/json")
	public ResponseEntity<SubCategory> get(@PathVariable("id") long id) {
		try {
			SubCategory subcat = subCategoryRepository.findById(id).get();
			return new ResponseEntity<SubCategory>(subcat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<SubCategory>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/delete")
	public Map<String, String> delete(@PathVariable("id") long id) {
		subCategoryRepository.deleteById(id);
		HashMap<String, String> msg = new HashMap<>();
		msg.put("status", "success");
		msg.put("message", "Record Deleted");
		return msg;
	}

	ModelMapper modelmapper = new ModelMapper();

	@PostMapping(path = "/edit")
	public SubCategory edit(@RequestBody SubCategory subcat) {
		SubCategory subcat1 = subCategoryRepository.findById(subcat.getId()).get();
		modelmapper.map(subcat, subcat1);
		SubCategory subcat2 = subCategoryRepository.save(subcat1);
		return subcat2;
	}

}
