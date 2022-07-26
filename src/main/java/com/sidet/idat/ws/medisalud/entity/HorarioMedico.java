package com.sidet.idat.ws.medisalud.entity;

import lombok.Data;

@Data
public class HorarioMedico {
	
	private Integer horarioMedicoId;
	private PersonalMedico personalMedico;
	private String tmLunes;
	private String tmMartes;
	private String tmMiercoles;
	private String tmJueves;
	private String tmViernes;
	private String tmSabado;
	private String tmDomingo;
	private String ttLunes;
	private String ttMartes;
	private String ttMiercoles;
	private String ttJueves;
	private String ttViernes;
	private String ttSabado;
	private String ttDomingo;
	private String tnLunes;
	private String tnMartes;
	private String tnMiercoles;
	private String tnJueves;
	private String tnViernes;
	private String tnSabado;
	private String tnDomingo;
	
}
