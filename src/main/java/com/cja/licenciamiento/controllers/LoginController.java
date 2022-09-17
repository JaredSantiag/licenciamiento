package com.cja.licenciamiento.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cja.licenciamiento.dto.LoginDTO;
import com.cja.licenciamiento.dto.RegistroDTO;
import com.cja.licenciamiento.entities.Role;
import com.cja.licenciamiento.entities.Usuario;
import com.cja.licenciamiento.repositories.RoleRepository;
import com.cja.licenciamiento.repositories.UsuarioRepository;
import com.cja.licenciamiento.security.JWTAuthResonseDTO;
import com.cja.licenciamiento.security.JwtTokenProvider;

@RestController
@RequestMapping("/licenciamiento/login")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/iniciarsesion")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {

		Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Obtenemos el token del jwtTokenProvider
		String token = jwtTokenProvider.generarToken(authentication);

		return ResponseEntity.ok(new JWTAuthResonseDTO(token));
	}

	@PostMapping("/registrar")
	public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO) {

		if (usuarioRepository.existsByUsername(registroDTO.getUsername())) {
			return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
		}

		if (usuarioRepository.existsByEmail(registroDTO.getEmail())) {
			return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
		}

		Usuario usuario = new Usuario();
		usuario.setNombre(registroDTO.getNombre());
		usuario.setUsername(registroDTO.getUsername());
		usuario.setEmail(registroDTO.getEmail());
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

		try {
			Role roles = roleRepository.findByNombre(registroDTO.getRole()).get();
			usuario.setRoles(Collections.singleton(roles));
		} catch (Exception error) {
			return new ResponseEntity<>("El Role no es valido", HttpStatus.BAD_REQUEST);
		}

		usuarioRepository.save(usuario);
		return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
	}
}
