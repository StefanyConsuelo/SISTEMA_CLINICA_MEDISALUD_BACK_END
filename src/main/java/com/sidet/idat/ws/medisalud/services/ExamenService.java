package com.sidet.idat.ws.medisalud.services;

import com.sidet.idat.ws.medisalud.entity.dto.ExamenDTO;

public interface ExamenService<T> extends CRUDMybatis<T> {

	ExamenDTO editarResultado( ExamenDTO examenDTO );
	Boolean finalizarExamen( Integer examenId );
}
