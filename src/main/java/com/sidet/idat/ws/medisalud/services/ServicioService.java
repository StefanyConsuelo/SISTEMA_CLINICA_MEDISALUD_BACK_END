package com.sidet.idat.ws.medisalud.services;

import java.util.List;

import com.sidet.idat.ws.medisalud.entity.dto.ServicioDTO;

public interface ServicioService<T> extends CRUDMybatis<T>{

	List<ServicioDTO> listarPorAreaMedicaId( Integer areaMedicaId );
	
}
