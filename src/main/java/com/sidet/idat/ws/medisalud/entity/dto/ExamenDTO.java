package com.sidet.idat.ws.medisalud.entity.dto;

import com.sidet.idat.ws.medisalud.entity.PersonalMedico;

import lombok.Data;

@Data
public class ExamenDTO {

	private Integer examenId;
	private String resultado;
	private ServicioDTO servicio;
	private HistoriaClinicaDTO historiaClinica;
	private Integer historiaClinicaId;
	private String codigo;
	private String estado;
	private PersonalMedico personalMedico;
	private String fechaCreacion;
}
