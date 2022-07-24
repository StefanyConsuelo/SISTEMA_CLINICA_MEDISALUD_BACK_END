package com.sidet.idat.ws.medisalud.entity;

import lombok.Data;

@Data
public class TestCliente {

	private Integer testClienteId;
	private String nombre;
	private String numeroDocumento;
	private TestTipoCliente testTipoCliente;
	private TestDireccionCliente testDireccionCliente;
}
