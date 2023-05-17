package com.example.group.dto;

public class StateDTO {
	long id;

	String countryName;
	String stateName;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", countryName=" + countryName + ", stateName=" + stateName + "]";
	}

}
