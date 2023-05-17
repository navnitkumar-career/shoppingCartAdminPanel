package com.example.group;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShoppingCartAdminPanelApplication {

	
	@Bean
	public ModelMapper getModelMapper() {
		return  new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartAdminPanelApplication.class, args);
	}

}
