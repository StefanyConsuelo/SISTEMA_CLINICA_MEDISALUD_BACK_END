package com.sidet.idat.ws.medisalud.entity;

import lombok.Data;

@Data
public class DetalleMedicacion {

	private Integer detalleMedicacionId;
	private Integer frecuencia;
	private String dosis;
	private String via;
	private Integer siguienteDosis;
	private String indicaciones;
	private Integer duracion;
	private Medicacion medicacion;
	private Medicamento medicamento;

}
