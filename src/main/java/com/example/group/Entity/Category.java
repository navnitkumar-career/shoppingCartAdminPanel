package com.example.group.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	long id;
	String Cat_Name;
	String description;

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", Cat_Name=" + Cat_Name + ", description=" + description + "]";
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return Cat_Name;
	}

	public void setName(String Cat_Name) {
		this.Cat_Name = Cat_Name;
	}

}
