package com.sidet.idat.ws.medisalud.entity;

import lombok.Data;

@Data
public class MotivoConsulta {
	
	private Integer motivoConsultaId;
	private String tipoEnfermedad;
	private String sintomasPrincipales;
	private String relato;
	private String apetito;
	private String sedantes;
	private String orina;
	private HistoriaClinica historiaClinica;
}
