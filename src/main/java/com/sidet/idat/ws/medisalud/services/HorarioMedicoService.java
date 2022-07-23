package com.sidet.idat.ws.medisalud.services;

import java.util.List;

import com.sidet.idat.ws.medisalud.entity.dto.HorarioMedicoDTO;

public interface HorarioMedicoService<T> extends CRUDMybatis<T>{

	List<HorarioMedicoDTO> listarPorServicioId( Integer servicioId );
	
}
