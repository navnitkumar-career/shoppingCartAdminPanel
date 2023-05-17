package com.example.group.dto;

public class CountryDTO {
	long id;
	String CountryName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountryName() {
		return CountryName;
	}

	public void setCountryName(String countryName) {
		CountryName = countryName;
	}

	@Override
	public String toString() {
		return "CountryDTO [id=" + id + ", CountryName=" + CountryName + "]";
	}

}
