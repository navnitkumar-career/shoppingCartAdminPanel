package com.example.group.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.group.Entity.City;
import com.example.group.Entity.State;
import com.example.group.dto.CityDTO;
import com.example.group.repository.CityRepository;
import com.example.group.repository.StateRepository;
import com.example.group.service.CityService;

@Service
public class CityServiceimpl implements CityService {

	public final Logger logger = LoggerFactory.getLogger(CityServiceimpl.class);

	@Autowired
	CityRepository cityRepository;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	@Transactional
	public List<CityDTO> getList() {
		List<City> city = cityRepository.findAll();
		List<CityDTO> city1 = city.stream().map(t -> {
			CityDTO city2 = new CityDTO();
			city2.setCountryName(t.getState().getCountry().getCountryName());
			city2.setCountryId(t.getState().getCountry().getId());
			modelMapper.map(t, city2);
			return city2;
		}).collect(Collectors.toList());
		return city1;
	}

	@Override
	@Transactional
	public CityDTO Add(CityDTO city) {
		City city1 = new City();
		modelMapper.map(city, city1);
		State s1 = stateRepository.getById(city.getStateId());
		city1.setState(s1);
		cityRepository.save(city1);
		modelMapper.map(city1, city);
		return city;
	}

	@Override
	public CityDTO Update(CityDTO city) {
		City city1 = cityRepository.findById(city.getId()).get();
		modelMapper.map(city, city1);
		cityRepository.save(city1);
		modelMapper.map(city1, city);
		return city;
	}

	@Override
	public void delete(long id) {
		cityRepository.deleteById(id);
	}

	@Override
	public CityDTO GetId(long id) {
		City city = cityRepository.findById(id).get();
		CityDTO city1 = new CityDTO();

		city1.setCountryId(city.getState().getCountry().getId());
		city1.setCountryName(city.getState().getCountry().getCountryName());

		modelMapper.map(city, city1);
		return city1;
	}

	@Override
	public List<CityDTO> getListByState(int state) {
		List<City> city = cityRepository.findByStateId(state);
		List<CityDTO> city1 = city.stream().map(t -> {
			CityDTO city2 = new CityDTO();
			modelMapper.map(t, city2);
			return city2;
		}).collect(Collectors.toList());
		return city1;
	}

}
