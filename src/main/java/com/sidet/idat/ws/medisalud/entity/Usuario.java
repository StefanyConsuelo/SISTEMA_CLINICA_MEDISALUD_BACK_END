package com.sidet.idat.ws.medisalud.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Usuario implements Serializable{

	private static final long serialVersionUID = 3946438794489039525L;
	
	private Integer usuarioId;
	private String username;
	private String password;
	private Boolean enabled;
	private List<Rol> roles;
	private List<Modulo> modulos;
	private Persona persona;
	private Perfil perfil;
	private Boolean activo;
}
