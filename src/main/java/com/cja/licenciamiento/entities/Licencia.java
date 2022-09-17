package com.cja.licenciamiento.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "licencias",  uniqueConstraints = {@UniqueConstraint(columnNames = { "num_licencia" })})
public class Licencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre_empresa", length = 80)
	private String nombreEmpresa;

	@Column(length = 13)
	private String rfc;

	@Column(length = 80)
	private String direccion;

	@Column(length = 80)
	private String correo;

	private Long telefono;

	@Column(length = 20)
	private String aplicacion;

	@Column(name = "num_licencia", length = 25)
	private String numeroLicencia;

	@Column(name = "cant_usuarios")
	private Integer cantidadUsuarios;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio")
	private Date fechaInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fin")
	private Date fechaFin;

	Licencia() {

	}

	public Licencia(Integer id, String nombreEmpresa, String rfc, String direccion, String correo, Long telefono,
			String aplicacion, String numeroLicencia, Integer cantidadUsuarios, Date fechaInicio, Date fechaFin) {
		super();
		this.id = id;
		this.nombreEmpresa = nombreEmpresa;
		this.rfc = rfc;
		this.direccion = direccion;
		this.correo = correo;
		this.telefono = telefono;
		this.aplicacion = aplicacion;
		this.numeroLicencia = numeroLicencia;
		this.cantidadUsuarios = cantidadUsuarios;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getNumeroLicencia() {
		return numeroLicencia;
	}

	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}

	public Integer getCantidadUsuarios() {
		return cantidadUsuarios;
	}

	public void setCantidadUsuarios(Integer cantidadUsuarios) {
		this.cantidadUsuarios = cantidadUsuarios;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

}