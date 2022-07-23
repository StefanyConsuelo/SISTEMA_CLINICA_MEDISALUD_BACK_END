package com.sidet.idat.ws.medisalud.entity;

import lombok.Data;

@Data
public class Provincia {

	private Integer provinciaId;
	private String nombre;
	private Departamento departamento;
	
}
