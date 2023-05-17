package com.example.group.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.group.Entity.Country;
import com.example.group.dto.CountryDTO;
import com.example.group.repository.CountryRepository;
import com.example.group.service.CountryService;

@Service
public class CountryServiceimpl implements CountryService {

	public final Logger logger = LoggerFactory.getLogger(CountryServiceimpl.class);

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	CountryRepository countryRepository;

	public List<CountryDTO> countryList() {
		List<Country> country = countryRepository.findAll();
		System.out.println(country);
		List<CountryDTO> countrydto = country.stream().map(t -> {
			CountryDTO c = new CountryDTO();
			modelMapper.map(t, c);
			return c;
		}).collect(Collectors.toList());

		return countrydto;
	}

	@Override
	public CountryDTO add(CountryDTO country) {
		Country con = new Country();
		modelMapper.map(country, con);
		con = countryRepository.save(con);
		modelMapper.map(con, country);

		return country;
	}

	public CountryDTO get(long id) {
		Country country = countryRepository.findById(id).get();
		CountryDTO country1 = new CountryDTO();
		modelMapper.map(country, country1);
		return country1;
	}

	@Override
	public CountryDTO update(CountryDTO country) {
		Country country1 = countryRepository.findById(country.getId()).get();
		modelMapper.map(country, country1);
		countryRepository.save(country1);
		modelMapper.map(country1, country);
		return country;
	}

	@Override
	public void delete(long id) {
		countryRepository.deleteById(id);
	}

}
