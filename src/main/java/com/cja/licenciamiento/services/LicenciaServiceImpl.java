package com.cja.licenciamiento.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cja.licenciamiento.entities.Licencia;
import com.cja.licenciamiento.repositories.LicenciaRepository;
import com.cja.licenciamiento.exceptions.ResourceNotFoundException;
import com.cja.licenciamiento.dto.LicenciaDTO;

@Service
public class LicenciaServiceImpl implements LicenciaService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LicenciaRepository licenciaRepository;
	
	@Override
	public LicenciaDTO obtenerLicenciaPorId(Integer id) {
		Licencia licencia = licenciaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Licencia", "numero", String.valueOf(id)));
		return mapearDTO(licencia);
	}
	
	@Override
	public Boolean buscarLicenciaPorNumero(String numeroLicencia) {
		if(licenciaRepository.findByNumeroLicencia(numeroLicencia).isPresent()) {
			Licencia licencia = licenciaRepository.findByNumeroLicencia(numeroLicencia).orElse(null);
			long miliseconds = System.currentTimeMillis();
			Date fecha_actual = new Date(miliseconds);
			if (fecha_actual.before(licencia.getFechaFin())) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	@Override
	public LicenciaDTO crearLicencia(LicenciaDTO licenciaDTO) {
		Licencia licencia = mapearEntidad(licenciaDTO);
		Licencia nuevaLicencia = licenciaRepository.save(licencia);
		LicenciaDTO licenciaRespuesta = mapearDTO(nuevaLicencia);
		return licenciaRespuesta;
	}
	
	@Override
	public LicenciaDTO actualizarLicencia(Integer id, LicenciaDTO licenciaDTO) {
		Licencia licencia = licenciaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Licencia", "numero", String.valueOf(id)));
		licencia.setNombreEmpresa(licenciaDTO.getNombreEmpresa());
		licencia.setRfc(licenciaDTO.getRfc());
		licencia.setDireccion(licenciaDTO.getDireccion());
		licencia.setCorreo(licenciaDTO.getCorreo());
		licencia.setTelefono(licenciaDTO.getTelefono());
		licencia.setAplicacion(licenciaDTO.getAplicacion());
		licencia.setNumeroLicencia(licenciaDTO.getNumeroLicencia());
		licencia.setCantidadUsuarios(licenciaDTO.getCantidadUsuarios().intValue());
		licencia.setFechaInicio(licenciaDTO.getFechaInicio());
		licencia.setFechaFin(licenciaDTO.getFechaFin());
		return mapearDTO(licencia);
	}
	
	@Override
	public List<LicenciaDTO> obtenerLicencias() {
		List<Licencia> licencias = licenciaRepository.findAll();
		return licencias.stream().map(firma -> mapearDTO(firma)).collect(Collectors.toList());
	}

	// Convierte Entidad a DTO
	private LicenciaDTO mapearDTO(Licencia licencia) {
		LicenciaDTO licenciaDTO = modelMapper.map(licencia, LicenciaDTO.class);
		return licenciaDTO;
	}

	// Convierte de DTO a Entidad
	private Licencia mapearEntidad(LicenciaDTO licenciaDTO) {
		Licencia licencia = modelMapper.map(licenciaDTO, Licencia.class);
		return licencia;
	}

}
