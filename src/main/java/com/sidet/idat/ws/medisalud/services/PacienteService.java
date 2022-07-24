package com.sidet.idat.ws.medisalud.services;

import com.sidet.idat.ws.medisalud.entity.dto.PacienteDTO;
import com.sidet.idat.ws.medisalud.entity.dto.TriajePacienteDTO;

public interface PacienteService<T> extends CRUDMybatis<T> {

	PacienteDTO buscarPorNumeroDocumento( String numeroDocumento, String tipoDocumento );
	
	PacienteDTO actualizarTriaje( PacienteDTO pacienteDTO );
	
	TriajePacienteDTO listarTriajeUltimo( Integer idHistoriaClinica );	
}
