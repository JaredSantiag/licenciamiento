package com.cja.licenciamiento.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cja.licenciamiento.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	public Optional<Role> findByNombre(String nombre);

}
