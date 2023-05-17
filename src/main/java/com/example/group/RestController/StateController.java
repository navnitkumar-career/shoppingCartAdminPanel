package com.example.group.RestController;

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

import com.example.group.dto.StateDTO;
import com.example.group.service.StateService;

@RestController
@RequestMapping("/state")
public class StateController {
	public final Logger logger = LoggerFactory.getLogger(StateController.class);

	@Autowired
	StateService stateService;

	@GetMapping(path = "/getlist", produces = "application/json")
	public List<StateDTO> getList() {
		List<StateDTO> stateList = stateService.getList();
		return stateList;
	}

	@PostMapping(path = "/add", produces = "application/json", consumes = "application/json")
	public StateDTO addsave(@RequestBody StateDTO stat) {
		StateDTO stat1 = stateService.add(stat);
		return stat1;
	}

	@GetMapping(path = "/delete/{id}", produces = "application/json")
	public void delete(@PathVariable("id") long id) {
		stateService.delete(id);
	}

	@GetMapping(path = "/get/{id}")
	public StateDTO getId(@PathVariable("id") long id) {
		StateDTO stat = stateService.get(id);
		return stat;
	}

	@PostMapping(path = "/edit", produces = "application/json", consumes = "application/json")
	public StateDTO editsave(@RequestBody StateDTO stat) {

		StateDTO stat1 = stateService.update(stat);
		return stat1;
	}

}
