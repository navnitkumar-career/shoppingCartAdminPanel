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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.example.group.Entity.Category;
import com.example.group.Entity.Product;
import com.example.group.Entity.SubCategory;
import com.example.group.repository.CategoryRepository;
import com.example.group.repository.ProductRepository;
import com.example.group.repository.SubCategoryRepository;
import com.example.group.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	public final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductRepository productrepository;

	@Autowired
	ProductService productService;

	@Autowired
	CategoryRepository categoryrepository;

	@Autowired
	SubCategoryRepository subCategoryRepository;

	@RequestMapping()
	public RedirectView dfl() {

		return new RedirectView("/product/view");
	}

	@GetMapping("/getsubcatbycat/{id}")
	public String getsubcatbycat(Model model, @PathVariable("id") long id) {

		try {
			Thread.currentThread();
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		List<SubCategory> subCatList = subCategoryRepository.findByCategoryId(id);
		model.addAttribute("subCatList", subCatList);
		return "product-getsubcatbycat.html";
	}

//	@GetMapping("/view")
//	public String view(Model model) {
//		List<Product> prolist = productrepository.findAll();
//		model.addAttribute("prolist", prolist);
//
//		List<SubCategory> subcategory = subCategoryRepository.findAll();
//		model.addAttribute("subcatlist", subcategory);
//
//		List<Category> category = categoryrepository.findAll();
//		model.addAttribute("catlist", category);
//		return "product-view.html";
//	}
	@GetMapping("/view")
	public String view(Model model) {
		List<Product> prolist = productrepository.findAll();
		List<Map<String, Object>> mlist = new ArrayList<>();

		for (Product p : prolist) {
			SubCategory subCat = subCategoryRepository.findById(p.getSubcategory()).get();
			Category cat1 = categoryrepository.findById(subCat.getCategoryId()).get();

			Map<String, Object> param = new HashMap<>();
			param.put("id", p.getId());
			param.put("mrp", p.getMrp());
			param.put("proName", p.getProduct_name());
			param.put("quantity", p.getQuantity());
			param.put("subCatName", subCat.getName());

			param.put("catName", cat1.getName());

			mlist.add(param);
		}

		model.addAttribute("mlist", mlist);
		return "product-view2.html";
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping("/view1")
	public String view1(Model model) {

		String sql = "SELECT s.name subCatName,c.Cat_name catName,p.id,p.Product_name proName,p.mrp,p.quantity \r\n"
				+ "FROM product p \r\n" + "INNER JOIN sub_category s ON p.subcategoryid=s.id\r\n"
				+ "INNER JOIN category c ON c.id=s.category_id\r\n" + "order by p.id";
		
		List<Map<String, Object>> mlist = jdbcTemplate.queryForList(sql);
		model.addAttribute("mlist", mlist);
		return "product-view2.html";
	}

	@GetMapping("/add")
	public String add(Model model) {
		List<Category> list = categoryrepository.findAll();
		model.addAttribute("catList", list);

		return "product-add.html";
	}

	@PostMapping("/add-save")
	public RedirectView addsave(@RequestParam("subcategory") long subcategory, @RequestParam("mrp") long mrp,
			@RequestParam("product_name") String product_name, @RequestParam("quantity") int quantity) {
		Product pro = new Product();
		List<Product> proList = productrepository.findAll();

		for (Product prod : proList) {
			if (prod.getSubcategory() != subcategory) {
				continue;
			}
			if (prod.getProduct_name().equalsIgnoreCase(product_name)) {
				return new RedirectView("/product/view");
			}

		}
		pro.setSubcategory(subcategory);
		pro.setMrp(mrp);
		pro.setProduct_name(product_name);
		pro.setQuantity(quantity);
		productrepository.save(pro);
		return new RedirectView("/product");
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("id") long id, Model model) {

		Product pro = productrepository.findById(id).get();
		model.addAttribute("prolist", pro);

		SubCategory subCat = subCategoryRepository.findById(pro.getSubcategory()).get();

		List<Category> catlist = categoryrepository.findAll();
		model.addAttribute("catList", catlist);

		List<SubCategory> subCatList = subCategoryRepository.findByCategoryId(subCat.getCategoryId());

		model.addAttribute("selectedCatId", subCat.getCategoryId());
		model.addAttribute("subCatList", subCatList);
		return "product-edit.html";
	}

	@PostMapping("/edit-save")
	public RedirectView editsave(@RequestParam("id") long id, @RequestParam("mrp") long mrp,
			@RequestParam("subcategory") long subcategory, @RequestParam("product_name") String product_name,
			@RequestParam("quantity") long quantity) {
		Product product = productrepository.findById(id).get();
		List<Product> prodlist = productrepository.findAll();
		for (Product pro : prodlist) {
			if (pro.getId() == id) {
				continue;
			}
			if (pro.getSubcategory() != subcategory) {
				continue;
			}
			if (pro.getProduct_name().equalsIgnoreCase(product_name)) {
				return new RedirectView("/product/view");
			}
		}
		product.setMrp(mrp);
		product.setSubcategory(subcategory);
		product.setQuantity(quantity);
		product.setProduct_name(product_name);
		productrepository.save(product);
		return new RedirectView("/product");
	}

	@GetMapping("/delete/{id}")
	@ResponseBody
	public RedirectView delete(@PathVariable("id") long id) {
		productrepository.deleteById(id);
		return new RedirectView("/product");
	}
}
