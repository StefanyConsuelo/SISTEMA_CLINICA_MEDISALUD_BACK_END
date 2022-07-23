package com.sidet.idat.ws.medisalud.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Paciente extends Persona {

	private Integer pacienteId;
	private String genero;
	private String estadoCivil;
	private String grupoSanguineo;
	private String canal;
	private String religion;
	private String lugarNacimiento;
	private String tipoPaciente;
	private String direccion;
	private String responsableNombresApellidos;
	private String responsableParentescoPaciente;
	private String responsableTelefono;
	private Distrito distrito;	
	private HistoriaClinica historiaClinica;
	private String peso;
	private String talla;
	private String fechaActualTriaje;
}
