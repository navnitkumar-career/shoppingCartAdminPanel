package com.example.group.Contoller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.group.dto.CountryDTO;
import com.example.group.service.CountryService;

@RestController
@RequestMapping("/country")
public class RestCountryController {

	public final Logger logger = LoggerFactory.getLogger(RestCountryController.class);

	@Autowired
	CountryService countryService;

	@GetMapping(path = "/get-list", produces = "application/json")
	public List<CountryDTO> getlist() {
		List<CountryDTO> countrylist = countryService.countryList();

		return countrylist;
	}

	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public CountryDTO addsave(@RequestBody CountryDTO country) {
		CountryDTO country1 = countryService.add(country);

		return country1;
	}

	@GetMapping(path = "/get/{id}", produces = "application/json")
	public CountryDTO getid(@PathVariable("id") long id) {
		CountryDTO country = countryService.get(id);
		return country;
	}

	@GetMapping(path = "/delete/{id}", produces = "application/json")
	public void delete(@PathVariable("id") long id) {
		countryService.delete(id);
	}

	@PostMapping(path = "/edit", consumes = "application/json", produces = "application/json")
	public CountryDTO edit(@RequestBody CountryDTO country) {
		CountryDTO country1 = countryService.update(country);
		return country1;
	}

}
