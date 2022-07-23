package com.sidet.idat.ws.medisalud.services;

import com.sidet.idat.ws.medisalud.entity.dto.PacienteDTO;

public interface PacienteService<T> extends CRUDMybatis<T> {

	PacienteDTO buscarPorNumeroDocumento( String numeroDocumento, String tipoDocumento );
	
	PacienteDTO actualizarTriaje( PacienteDTO pacienteDTO );
	
}
