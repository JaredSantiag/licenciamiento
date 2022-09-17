package com.cja.licenciamiento.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cja.licenciamiento.dto.LicenciaDTO;
import com.cja.licenciamiento.services.LicenciaService;

@RestController
@RequestMapping("licenciamiento/licencias")
public class LicenciaController {

	@Autowired
	private LicenciaService licenciaService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping()
	public List<LicenciaDTO> mostrarLicencias() {
		return licenciaService.obtenerLicencias();
	}

	//Endpoint exclusivo para ser consumido por terceros
	@GetMapping("/numero/{numero}")
	public ResponseEntity<String> obtenerLicenciaPorNumeroLicencia(@PathVariable(name = "numero") String numeroLicencia) {
		if(licenciaService.buscarLicenciaPorNumero(numeroLicencia)) {
			return new ResponseEntity<>("Licencia valida", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Licencia no valida", HttpStatus.UNAUTHORIZED);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<LicenciaDTO> guardarLicencia(@RequestBody LicenciaDTO licenciaDTO) {
		return new ResponseEntity<>(licenciaService.crearLicencia(licenciaDTO), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<LicenciaDTO> actualizarLicencia(@Valid @RequestBody LicenciaDTO licenciaDTO,
			@PathVariable(name = "id") int id){
		LicenciaDTO licenciaRespuesta = licenciaService.actualizarLicencia(id, licenciaDTO);
		return new ResponseEntity<>(licenciaRespuesta, HttpStatus.OK);
	}

}
