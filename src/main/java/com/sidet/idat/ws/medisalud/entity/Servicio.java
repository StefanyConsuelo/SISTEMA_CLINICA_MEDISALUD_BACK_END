package com.sidet.idat.ws.medisalud.entity;

import lombok.Data;

@Data
public class Servicio {
	
	private Integer servicioId;
	private String nombre;
	private AreaMedica areaMedica;
	
}