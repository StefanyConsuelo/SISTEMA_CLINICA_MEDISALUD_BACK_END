package com.sidet.idat.ws.medisalud.entity;

import lombok.Data;

@Data
public class Persona {
	
	private Integer personaId;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String tipoDocumento;
	private String numeroDocumento;
	private String fechaNacimiento;
	private String telefonoFijo;
	private String celular;
	private String correo;
	
}
