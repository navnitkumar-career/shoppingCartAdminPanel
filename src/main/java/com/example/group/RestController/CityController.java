package com.example.group.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.group.dto.CityDTO;
import com.example.group.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController {

	@Autowired
	CityService cityService;

	@GetMapping(path = "/getlist", produces = "application/json")
	public List<CityDTO> getList() {
		List<CityDTO> city = cityService.getList();
		
		return city;
	}

	@PostMapping(path = "/add", produces = "application/json", consumes = "application/json")
	public CityDTO add(@RequestBody CityDTO city) {
		CityDTO City1 = cityService.Add(city);
		return City1;
	}

	@GetMapping(path = "/delete/{id}", produces = "application/json")
	void delete(@PathVariable("id") long id) {
		cityService.delete(id);
	}

	@GetMapping(path = "/get/{id}", produces = "application/json")
	public CityDTO getId(@PathVariable("id") long id) {
		CityDTO city = cityService.GetId(id);
		return city;
	}

	@PostMapping(path = "/edit", consumes = "application/json", produces = "application/json")
	public CityDTO edit(@RequestBody CityDTO city) {
		CityDTO city1 = cityService.Update(city);
		return city1;
	}

	@GetMapping(path = "state/{id}", produces = "application/json")
	public List<CityDTO> getListByState(@PathVariable("id") int state) {
		List<CityDTO> city = cityService.getListByState(state);

		return city;
	}

}
