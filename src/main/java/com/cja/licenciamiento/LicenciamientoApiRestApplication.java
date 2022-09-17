package com.cja.licenciamiento;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LicenciamientoApiRestApplication {
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper ();
	}

	public static void main(String[] args) {
		SpringApplication.run(LicenciamientoApiRestApplication.class, args);
	}

}
