package com.example.group.service;

import java.util.List;

import com.example.group.dto.CityDTO;

public interface CityService {

	List<CityDTO> getList();

	CityDTO Add(CityDTO city);

	CityDTO Update(CityDTO city);

	void delete(long id);

	CityDTO GetId(long id);

	List<CityDTO> getListByState(int state);
}
