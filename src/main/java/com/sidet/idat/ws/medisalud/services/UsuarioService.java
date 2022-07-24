package com.sidet.idat.ws.medisalud.services;

public interface UsuarioService<T> extends CRUDMybatis<T>{
	
	Boolean bloquear( Integer usuarioId );
	
	Boolean desbloquear( Integer usuarioId );
}
