package com.sidet.idat.ws.medisalud.entity;

import lombok.Data;

@Data
public class Examen {

	private Integer examenId;
	private String resultado;
	private Servicio servicio;
	private HistoriaClinica historiaClinica;
	private String estado;
	private PersonalMedico personalMedico;
	private String fechaCreacion;
}
