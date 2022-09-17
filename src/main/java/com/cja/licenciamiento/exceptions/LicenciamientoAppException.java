package com.cja.licenciamiento.exceptions;

import org.springframework.http.HttpStatus;

public class LicenciamientoAppException extends RuntimeException{
	private static final long serialVersionUID = 1;
	
	private HttpStatus estado;
	private String mensaje;
	
	public LicenciamientoAppException(HttpStatus estado, String mensaje) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
	}

	public LicenciamientoAppException(HttpStatus estado, String mensaje, String mensaje1) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
		this.mensaje = mensaje1;
	}

	public HttpStatus getEstado() {
		return estado;
	}

	public void setEstado(HttpStatus estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
