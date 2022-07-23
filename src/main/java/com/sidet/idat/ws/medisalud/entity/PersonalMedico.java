package com.sidet.idat.ws.medisalud.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PersonalMedico extends Persona{

	private Integer personalMedicoId;
	private String profesion;
	private String documentoProfesional;
	private String numeroDocumentoProfesional;
	private String estadoLaboral;
	private Servicio servicio;
}