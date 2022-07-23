package com.sidet.idat.ws.medisalud.services;

import java.util.List;

import com.sidet.idat.ws.medisalud.entity.dto.ProvinciaDTO;

public interface ProvinciaService {
	
	List<ProvinciaDTO> listarPorDepartamentoId( Integer departamentoId );
	
}
