package com.cja.licenciamiento.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cja.licenciamiento.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	public Optional<Usuario> findByEmail(String email);
	
	public Optional<Usuario> findByUsernameOrEmail(String username, String email);
	
	public Optional<Usuario> findByUsername (String username);
	
	public Boolean existsByUsername (String username);
	
	public Boolean existsByEmail(String email);
	
}
