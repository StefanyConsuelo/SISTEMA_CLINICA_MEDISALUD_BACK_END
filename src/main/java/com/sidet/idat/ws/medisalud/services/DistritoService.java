package com.sidet.idat.ws.medisalud.services;

import java.util.List;

import com.sidet.idat.ws.medisalud.entity.dto.DistritoDTO;

public interface DistritoService {
	
	List<DistritoDTO> listarPorProvinciaId( Integer provinciaId );

}
