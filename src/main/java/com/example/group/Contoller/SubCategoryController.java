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
import com.example.group.Entity.SubCategory;
import com.example.group.repository.CategoryRepository;
import com.example.group.repository.SubCategoryRepository;

@Controller
@RequestMapping("/subcategory")
public class SubCategoryController {
	public final Logger logger = LoggerFactory.getLogger(SubCategoryController.class);
	@Autowired
	SubCategoryRepository subrepository;

	@Autowired
	CategoryRepository categoryRepository;

	@RequestMapping()
	public RedirectView dfl() {
		return new RedirectView("/subcategory/view");
	}

	@GetMapping("/view")
	public String view(Model model) {

		List<SubCategory> list = subrepository.findAll();
		model.addAttribute("list", list);

		List<Category> list1 = categoryRepository.findAll();
		model.addAttribute("catList", list1);
		return "subcategory-view.html";
	}

	@GetMapping("/add")
	public String add(Model model) {
		List<Category> list = categoryRepository.findAll();
		model.addAttribute("catList", list);
		return "subcategory-add.html";
	}

	@PostMapping("/add-save")
	public RedirectView addsave(@RequestParam("name") String name, @RequestParam("category_id") int category_id) {
		SubCategory subcategory = new SubCategory();
		List<SubCategory> subCatList = subrepository.findAll();
		for (SubCategory subCat : subCatList) {
			if (subCat.getName().equalsIgnoreCase(name)) {
				return new RedirectView("/subcategory");
			}
		}
		subcategory.setName(name);
		subcategory.setCategoryId(category_id);
		subrepository.save(subcategory);
		return new RedirectView("/subcategory");
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("id") long id, Model model) {
		SubCategory subcategory = subrepository.findById(id).get();
		model.addAttribute("list", subcategory);
		List<Category> category = categoryRepository.findAll();
		model.addAttribute("catList", category);

		return "subcategory-edit.html";
	}

	@PostMapping("/edit-save")
	public RedirectView editsave(@RequestParam("id") long id, @RequestParam("name") String name,
			@RequestParam("category_id") long category_id) {
		SubCategory subcategory = subrepository.findById(id).get();
		List<SubCategory> subCatList = subrepository.findAll();
		for (SubCategory subCat : subCatList) {
			if (subCat.getId() == id) {
				continue;
			}				
			if (subCat.getName().equalsIgnoreCase(name)) {
				return new RedirectView("/subcategory");
			}
		}
		subcategory.setName(name);
		subcategory.setCategoryId(category_id);
		subrepository.save(subcategory);
		return new RedirectView("/subcategory");
	}

	@GetMapping("/delete/{id}")
	@ResponseBody
	public RedirectView delete(@PathVariable("id") long id) {
		subrepository.deleteById(id);
		return new RedirectView("/subcategory");
	}
}
