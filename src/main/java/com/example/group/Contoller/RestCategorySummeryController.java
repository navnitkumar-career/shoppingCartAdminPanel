package com.example.group.Contoller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorysummery1")
public class RestCategorySummeryController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping(path = "/category", produces = "application/json")
	public List<Map<String, Object>> view() {
		String sql = "select c1.id,c1.Cat_Name ,count(distinct sc.id) total_sub_cat,count(distinct p1.id) total_prod "
				+ "from category c1 " + "left JOIN sub_category sc ON sc.category_id=c1.id "
				+ "left JOIN product p1 ON p1.subcategory=sc.id " + "group by c1.id,c1.Cat_Name ";

		List<Map<String, Object>> mlist = jdbcTemplate.queryForList(sql);

		return mlist;
	}

	@GetMapping(path = "/subcategory", produces = "application/json")
	public List<Map<String, Object>> view1() {
		String sql = "Select sc.id,sc.Name,count(distinct p1.id)total from sub_category sc left join product p1 ON p1.subcategory=sc.id group by sc.id,sc.Name";

		List<Map<String, Object>> mlist = jdbcTemplate.queryForList(sql);

		return mlist;
	}

}
