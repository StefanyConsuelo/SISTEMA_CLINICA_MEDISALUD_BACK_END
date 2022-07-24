package com.sidet.idat.ws.medisalud.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Hospitalizacion {

	private Integer hospitalizacionId;
	private Date fechaRegistro;
	private String preDiagnostico;
	private String procedencia;
	private String observacion;
	private String alta;
	private String estado;
	private Paciente paciente;
	private PersonalMedico personalMedico;
	private Cama cama;
	
}
