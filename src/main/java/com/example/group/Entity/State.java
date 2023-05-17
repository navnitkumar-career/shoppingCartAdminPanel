package com.example.group.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	Country country;
	// long countryId;
	String stateName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
//
//	public long getCountryId() {
//		return countryId;
//	}
//
//	public void setCountryId(long countryId) {
//		this.countryId = countryId;
//	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", country=" + country + ", stateName=" + stateName + "]";
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
