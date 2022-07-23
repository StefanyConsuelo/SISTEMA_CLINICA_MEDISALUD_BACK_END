package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sidet.idat.ws.medisalud.entity.TestCliente;
import com.sidet.idat.ws.medisalud.entity.TestDireccionCliente;

@Mapper
public interface TestClienteRepository {

	@Results( value = {
		// esto usamos en caso la columna de la base de datos no coincida 
		// con la propiedad de la clase.
		@Result( property = "numeroDocumento", column = "numero_documento" ),
		@Result( property = "testClienteId", column = "test_cliente_id" ),
		@Result( property = "testTipoCliente.testTipoClienteId", column = "test_tipo_cliente_id" ),
		@Result( property = "testTipoCliente.tipo", column = "tipo" ),
		@Result( property = "testDireccionCliente", column = "test_cliente_id",
			one = @One(select = "buscarTestDireccionCliente"))
	}, id = "resultTestClienteV1")
	@Select("select * from tb_test_clientes ttc inner join tb_test_tipo_cliente tttc on tttc.test_tipo_cliente_id = ttc.test_tipo_cliente_id ")
	List<TestCliente> listarTodos();
	
	@Insert("INSERT INTO tb_test_clientes (nombre, numero_documento, test_tipo_cliente_id) "
			+ "VALUES(#{nombre}, #{numeroDocumento}, #{testTipoCliente.testTipoClienteId})")
	@SelectKey(statement="SELECT LAST_INSERT_ID() as testClienteId", keyProperty="testClienteId", keyColumn = "test_cliente_id", resultType = int.class, before = false )
	void registrar( TestCliente testCliente );
	
	@Update("UPDATE tb_test_clientes SET nombre=#{nombre}, test_tipo_cliente_id= #{testTipoCliente.testTipoClienteId} WHERE test_cliente_id=#{testClienteId}")
	void actualizar( TestCliente testCliente );
	
	@Delete("DELETE FROM tb_test_clientes WHERE test_cliente_id=#{testClienteId}")
	void eliminar( Integer testClienteId );
	
	@ResultMap("resultTestClienteV1")
	@Select("select * from tb_test_clientes ttc inner join tb_test_tipo_cliente tttc on "
			+ "tttc.test_tipo_cliente_id = ttc.test_tipo_cliente_id where ttc.test_cliente_id = #{testClienteId} ")
	TestCliente buscarPorId( Integer testClienteId );
	
	@ResultMap("resultTestClienteV1")
	@Select("select * from tb_test_clientes ttc "
			+ "inner join tb_test_tipo_cliente tttc on tttc.test_tipo_cliente_id = ttc.test_tipo_cliente_id "
			+ "order by test_cliente_id"
			+ "limit #{inicio},#{fin}")
	List<TestCliente> listarPaginado( Integer inicio, Integer fin );
	
	@Select("select count(ttc.test_cliente_id) as total_filas from tb_test_clientes ttc")
	Integer contarTotalFilas();
	
	// este metodo es usado para el @One
	@Results( value = {
		@Result( property = "testDireccionClienteId", column = "test_direccion_cliente_id" )
	})
	@Select("select * from tb_test_direccion_cliente ttdc where ttdc.test_cliente_id = #{testClienteId} limit 1")
	TestDireccionCliente buscarTestDireccionCliente( Integer testClienteId );
}
