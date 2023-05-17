package com.example.group.Contoller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.example.group.Entity.Category;
import com.example.group.repository.CategoryRepository;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	public final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	CategoryRepository categoryrepository;

	@GetMapping()
	public RedirectView dfl() {
		return new RedirectView("/category/view");
	}

	@GetMapping("/view")
	public String view(Model model) {
		List<Category> list = categoryrepository.findAll();
		model.addAttribute("list", list);
		return "category-view.html";

	}

	@GetMapping("/error")
	public String error() {
		return "category-error.html";
	}

	@GetMapping("/add")
	public String add() {
		return "category-add.html";
	}

	@PostMapping("/add-save")
	public RedirectView addsave(@RequestParam("name") String name, @RequestParam("description") String description) {
		List<Category> categoryList = categoryrepository.findAll();
		Category category = new Category();
		for (Category cat : categoryList) {

			if (cat.getName().equalsIgnoreCase(name)) {
				return new RedirectView("/error");
			}
		}
		category.setName(name);
		category.setDescription(description);
		categoryrepository.save(category);
		return new RedirectView("/category");
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("id") long id, Model model) {
		Category category = categoryrepository.findById(id).get();
		model.addAttribute("list", category);
		return "category-edit.html";
	}

	@PostMapping("/edit-save")
	public RedirectView editSave(@RequestParam("id") long id, @RequestParam("name") String name,
			@RequestParam("description") String description) {
		Category category = categoryrepository.findById(id).get();
		List<Category> categoryList = categoryrepository.findAll();
		for (Category cat : categoryList) {
			if (cat.getId() == id) {
				continue;
			}
			if (cat.getName().equalsIgnoreCase(name)) {
				return new RedirectView("/category/error");
			}
		}
		category.setName(name);
		category.setDescription(description);
		categoryrepository.save(category);
		return new RedirectView("/category");
	}

	@GetMapping("/delete/{id}")
	@ResponseBody
	public RedirectView delete(@PathVariable("id") long id) {
		categoryrepository.deleteById(id);
		return new RedirectView("/category");
	}

}