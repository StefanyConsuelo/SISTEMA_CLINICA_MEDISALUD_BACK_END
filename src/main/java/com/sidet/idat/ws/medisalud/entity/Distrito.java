package com.sidet.idat.ws.medisalud.entity;

import lombok.Data;

@Data
public class Distrito {
	
	private Integer distritoId;
	private String nombre;
	private Provincia provincia;
	
}
