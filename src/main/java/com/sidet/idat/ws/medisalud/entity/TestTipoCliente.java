package com.sidet.idat.ws.medisalud.entity;

import java.util.List;

import lombok.Data;

@Data
public class TestTipoCliente {
	
	private Integer testTipoClienteId;
	private String tipo;
	private List<TestCliente> clientes;

}
