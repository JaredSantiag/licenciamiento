package com.cja.licenciamiento.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cja.licenciamiento.entities.Licencia;

public interface LicenciaRepository extends JpaRepository<Licencia, Integer> {
	
	public Optional<Licencia> findByNumeroLicencia(String numeroLicencia);

}
