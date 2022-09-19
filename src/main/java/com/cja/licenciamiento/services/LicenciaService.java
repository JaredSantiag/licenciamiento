package com.cja.licenciamiento.services;

import java.util.List;

import com.cja.licenciamiento.dto.LicenciaDTO;

public interface LicenciaService {

	public LicenciaDTO obtenerLicenciaPorId(Integer id);
	
	public Boolean esLicenciaValida(String numeroLicencia);
	
	public Integer obtenerCantidadUsuarios(String numeroLicencia);
	
	public List<LicenciaDTO> obtenerLicencias();
	
	public LicenciaDTO crearLicencia(LicenciaDTO licenciaDTO);
	
	public LicenciaDTO actualizarLicencia(Integer id, LicenciaDTO licenciaDTO);
	
}
