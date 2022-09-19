package com.cja.licenciamiento;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Esta API REST permite llevar el control de las licencias para los sistemas de
 * Acsystem y SIAL. Tambien tiene un Endpoint publico que devuleve si una
 * licencia es valida.
 * 
 * @author JaredSantiag
 * @since 2022-09-17
 */

@SpringBootApplication
public class LicenciamientoApiRestApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(LicenciamientoApiRestApplication.class, args);
	}

}
