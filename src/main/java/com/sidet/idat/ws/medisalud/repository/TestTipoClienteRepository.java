package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sidet.idat.ws.medisalud.entity.TestCliente;
import com.sidet.idat.ws.medisalud.entity.TestTipoCliente;

@Mapper
public interface TestTipoClienteRepository{


	@Results(value = {
		@Result( property = "testTipoClienteId", column = "test_tipo_cliente_id"),
		@Result( property = "clientes", column = "test_tipo_cliente_id", 
			many = @Many(select = "listarClientes"))
	})
	@Select("select * from tb_test_tipo_cliente")
	List<TestTipoCliente> listarTodos();
	
	@Results( value = {
		@Result( property = "numeroDocumento", column = "numero_documento" ),
		@Result( property = "testClienteId", column = "test_cliente_id" ),
	})
	@Select("select * from tb_test_clientes where test_tipo_cliente_id = #{testTipoClienteId}")
	List<TestCliente> listarClientes( Integer testTipoClienteId );
	
}
