package com.formacionbdi.springboot.app.commons.usuarios.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20)
	private String username;

	@Column(length = 60)
	private String password;
	private Boolean enabled;
	private String nombre;
	private String apellido;

	@Column(unique = true, length = 100)
	private String email;
	
	/*
	 * @ManyToMany
	 * Creamos una relación unidireccional.
	 * Para consultar los usuarios con sus roles desde esta clase.
	 * Si deseamos consultar usuarios desde los roles, debemos agregar el "@ManyToMany" en la clase "Role".
	 * Por debajo se crea la tabla intermedia "usuarios_roles"
	 * Conteniendo  ambas PK, por defecto con los atributos "usuario_id", "roles_id"
	 * 
	 * Para obtener el nombre del primer atributo "usuario_id":
	 * Usa el nombre de la clase "Usuario" aunque todo en minúsculas
	 * Concatena con guión bajo "_" seguido del atributo anotado con "@Id" que sería "id"
	 * 
	 * Para obtener el nombre del segundo atributo "roles_id":
	 * Usaría el atributo "roles" de la clase actual
	 * Concatendo con guión bajo "_" seguido del atributo anotado con "@Id" de la clase "Role" que sería "id"
	 * 
	 * @JoinTable
	 * Permite personalizar el nombre de la tabla intermedia y sus campos.
	 * 
	 * name = "usuarios_roles"
	 * Asiganamos a "usuarios_roles" como el  nombre de la tabla intermedia.
	 * 
	 * @JoinColumn(name="user_id")
	 * Indicamos el nombre de la primera llave foránea, correspondiente al PK de la clase actual "Usuario".
	 * 
	 * inverseJoinColumns = @JoinColumn(name="role_id")
	 * Indicamos el nombre de la segunda llave foránea, correspondiente al PK de la clase relacionada "Role".
	 * 
	 * uniqueConstraints
	 * Agrega un "constraint" para que el "user_id" y el "role_id" como pareja no se repitan.
	 * Completando así la condición de PK únicas en la tabla de la BD.
	 *  
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuarios_roles", 
	joinColumns = @JoinColumn(name="usuario_id"), inverseJoinColumns = @JoinColumn(name="role_id"), 
	uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "role_id"})})
	private List<Role> roles;

	private Integer intentos;
	
	// GETTERS ADN SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Integer getIntentos() {
		return intentos;
	}

	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
