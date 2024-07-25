package com.cargabatch.importador.entitys;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;

	private String nombre;
	private String apellido;
	private String accesCred;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private String email;
	private int telefono;
	private String usuario;
	private int sucursalId;
	private int rol_Id;
	private Date fechaNacimiento;
	private byte status;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getAccesCred() {
		return accesCred;
	}

	public void setAccesCred(String accesCred) {
		this.accesCred = accesCred;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	public int getRolId() {
		return rol_Id;
	}

	public void setRolId(int rolId) {
		this.rol_Id = rolId;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

}
