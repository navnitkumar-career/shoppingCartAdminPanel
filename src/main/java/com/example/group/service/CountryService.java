package com.example.group.service;

import java.util.List;

import com.example.group.dto.CountryDTO;

public interface CountryService {

	CountryDTO add(CountryDTO coun);

	CountryDTO update(CountryDTO coun);

	void delete(long id);

	CountryDTO get(long id);

	List<CountryDTO> countryList();

}
