package com.example.group.service;

import java.util.List;

import com.example.group.dto.StateDTO;

public interface StateService {

	StateDTO add(StateDTO stat);

	StateDTO update(StateDTO stat);

	void delete(long id);

	StateDTO get(long id);

	List<StateDTO> getList();

}
