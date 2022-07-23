package com.sidet.idat.ws.medisalud.services;

public interface HospitalizacionService<T> extends CRUDMybatis<T>{

	void darAlta( Integer hospitalizacionId );
	
	void darDisponibilidadCama( Integer hospitalizacionId, Integer camaId );
}
