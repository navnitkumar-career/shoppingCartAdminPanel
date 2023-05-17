package com.example.group.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.group.Entity.State;
import com.example.group.dto.StateDTO;
import com.example.group.repository.StateRepository;
import com.example.group.service.StateService;

@Service
public class StateServiceimpl implements StateService {

	public final Logger logger = LoggerFactory.getLogger(StateServiceimpl.class);

	@Autowired
	StateRepository stateRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public StateDTO add(StateDTO stat) {
		State stat1 = new State();
		modelMapper.map(stat, stat1);
		stateRepository.save(stat1);
		modelMapper.map(stat1, stat);
		return stat;
	}

	@Override
	public StateDTO update(StateDTO stat) {
		State stat1 = stateRepository.findById(stat.getId()).get();
		modelMapper.map(stat, stat1);
		stateRepository.save(stat1);
		modelMapper.map(stat1, stat);
		return stat;
	}

	@Override
	public void delete(long id) {
		stateRepository.deleteById(id);

	}

	@Override
	public StateDTO get(long id) {
		State stat = stateRepository.findById(id).get();
		StateDTO stat1 = new StateDTO();
		modelMapper.map(stat, stat1);
		return stat1;
	}

	@Override
	public List<StateDTO> getList() {
		List<State> state = stateRepository.findAll();
		List<StateDTO> state1 = state.stream().map(t -> {
			StateDTO stat = new StateDTO();
			modelMapper.map(t, stat);
			return stat;
		}).collect(Collectors.toList());
		return state1;
	}

}
