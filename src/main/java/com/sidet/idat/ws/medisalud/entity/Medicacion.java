package com.sidet.idat.ws.medisalud.entity;

import java.util.List;

import lombok.Data;

@Data
public class Medicacion {

	private Integer medicacionId;
	private HistoriaClinica historiaClinica;
	private List<DetalleMedicacion> detalle;
	private String fechaRegistro;
	private PersonalMedico personalMedico;
}
