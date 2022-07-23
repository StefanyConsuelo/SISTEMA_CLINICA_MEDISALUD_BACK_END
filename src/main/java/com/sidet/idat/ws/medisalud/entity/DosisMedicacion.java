package com.sidet.idat.ws.medisalud.entity;

import lombok.Data;

@Data
public class DosisMedicacion {

	private Integer dosisMedicacionId;
	private String fechaDosis;
	private Integer numeroDosis;
	private String estado;
	private DetalleMedicacion detalleMedicacion;
	
}
