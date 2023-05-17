package com.example.group.Contoller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.example.group.Entity.Category;
import com.example.group.Entity.Product;
import com.example.group.Entity.SubCategory;
import com.example.group.repository.CategoryRepository;
import com.example.group.repository.ProductRepository;
import com.example.group.repository.SubCategoryRepository;

@Controller
@RequestMapping("/categorysummery")
public class CategorySummeryController {
	public final Logger logger = LoggerFactory.getLogger(CategorySummeryController.class);

	@Autowired
	ProductRepository productrepository;

	@Autowired
	CategoryRepository categoryrepository;

	@Autowired
	SubCategoryRepository subcategoryrepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping("")
	public RedirectView dfl() {
		return new RedirectView("/categorysummery/view");
	}

	@GetMapping("/view")
	public String view(Model model) {

		List<Category> categoryList = categoryrepository.findAll();
		List<SubCategory> subCategoryList = subcategoryrepository.findAll();
		List<Product> productList = productrepository.findAll();

		List<Map<String, Object>> list = new ArrayList<>();

		for (Category cat : categoryList) {

			int count = 0;
			int countProd = 0;
			for (SubCategory subcat : subCategoryList) {
				if (cat.getId() == subcat.getCategoryId()) {
					count++;
					for (Product prod : productList) {
						if (prod.getSubcategory() == subcat.getId()) {
							countProd++;
						}
					}
				}
			}
			Map<String, Object> map = new HashMap<>();

			map.put("catId", cat.getId());
			map.put("catName", cat.getName());
			map.put("subCatCount", count);
			map.put("productCount", countProd);

			list.add(map);
		}
		model.addAttribute("list", list);
		return "categorysummery-view.html";
	}

	@GetMapping("/view1")
	public String view1(Model model) {

		String sql = "select c1.id,c1.Cat_Name ,count(distinct sc.id) total_sub_cat,count(distinct p1.id) total_prod "
				+ "from category c1 " + "left JOIN sub_category sc ON sc.category_id=c1.id "
				+ "left JOIN product p1 ON p1.subcategory=sc.id " + "group by c1.id,c1.Cat_Name ";
		List<Map<String, Object>> mlist = jdbcTemplate.queryForList(sql);
		model.addAttribute("mlist", mlist);

		return "categorysummery-view1.html";
	}

	@GetMapping("/view2")
	public String view2(Model model) {
		List<Category> catlist = categoryrepository.findAll();
		List<SubCategory> subCategoryList = subcategoryrepository.findAll();
		List<Product> proList = productrepository.findAll();
		List<Map<String, Object>> list = new ArrayList<>();
		for (Category cat : catlist) {
			int count = 0;
			int counter = 0;
			for (SubCategory subCat : subCategoryList) {
				if (cat.getId() == subCat.getCategoryId()) {
					count++;
					for (Product prod : proList) {

						if (prod.getSubcategory() == subCat.getId()) {
							counter++;
						}
					}
				}
			}
			Map<String, Object> map = new HashMap<>();
			map.put("id", cat.getId());
			map.put("Cat_Name", cat.getName());
			map.put("total_sub_cat", count);
			map.put("total_prod", counter);
			list.add(map);
		}

		model.addAttribute("mlist", list);
		return "categorysummery-view1.html";
	}

}