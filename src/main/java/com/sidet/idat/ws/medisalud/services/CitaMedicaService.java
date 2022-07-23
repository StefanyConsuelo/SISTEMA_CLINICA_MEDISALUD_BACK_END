package com.sidet.idat.ws.medisalud.services;

import java.util.List;

import com.sidet.idat.ws.medisalud.entity.dto.CitaMedicaDTO;

public interface CitaMedicaService<T> extends CRUDMybatis<T> {

	List<CitaMedicaDTO> listarPorServicioIdAndFechaCita( CitaMedicaDTO citaMedica );
	
	List<CitaMedicaDTO> listarPorPersonalMedico( Integer medicoId, String fecha);
	
	Boolean atender( Integer citaMedicaId );
}
