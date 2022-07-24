package com.sidet.idat.ws.medisalud.entity;

import java.util.List;

import lombok.Data;

@Data
public class Modulo {
	
	private Integer moduloId;
	private String nombre;
	private List<Rol> roles;
	private String rolesAgrupados;
	private String codigo;
}
