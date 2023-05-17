package com.example.group.Contoller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.example.group.Entity.Product;
import com.example.group.Entity.SubCategory;
import com.example.group.repository.ProductRepository;
import com.example.group.repository.SubCategoryRepository;

@Controller
@RequestMapping("/productsummery")
public class ProductSummeryController {
	@Autowired
	SubCategoryRepository subCategoryRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping()
	public RedirectView dfl() {
		return new RedirectView("/productsummery/view");
	}
	
	@GetMapping("/view")
	public String view(Model model) {
		List<SubCategory> sublist = subCategoryRepository.findAll();
		List<Product> prolist = productRepository.findAll();

		List<Map<String, Object>> list = new ArrayList<>();

		for (SubCategory sub : sublist) {
			int count = 0;
			Map<String, Object> map = new HashMap<>();
			for (Product pro : prolist) {
				if (sub.getId() == pro.getSubcategory()) {
					count++;
				}
			}
			map.put("subId", sub.getId());
			map.put("subName", sub.getName());
			map.put("productCount", count);
			list.add(map);
		}
		model.addAttribute("product_summery", list);
		return "product-summery.html";
	}

	@GetMapping("/view1")
	public String view1(Model model) {
		String sql = "Select sc.id,sc.Name,count(distinct p1.id)total from sub_category sc left join product p1 ON p1.subcategory=sc.id group by sc.id,sc.Name;";
		List<Map<String, Object>> productSummery = jdbcTemplate.queryForList(sql);
		model.addAttribute("prodsummery", productSummery);
		return "product-summery2.html";
	}

}


