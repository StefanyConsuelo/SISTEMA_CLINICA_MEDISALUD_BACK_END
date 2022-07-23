package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sidet.idat.ws.medisalud.entity.TestCliente;

@Mapper
public interface TestClienteRepositoryXML {

	List<TestCliente> listarTodos();
	
	void registrar( TestCliente testCliente );
	
	void actualizar( TestCliente testCliente );
	
	void eliminar( Integer testClienteId );
	
	TestCliente buscarPorId( Integer testClienteId );
	
	List<TestCliente> listarPaginado( Integer inicio, Integer fin );
	
	Integer contarTotalFilas();
}
