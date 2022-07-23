package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sidet.idat.ws.medisalud.entity.Medicamento;

@Mapper
public interface MedicamentoRepository {
	
	@Results(value = {
		@Result( property = "medicamentoId", column = "medicamento_id")
	})
	@Select("select * from tb_medicamento tm")
	List<Medicamento> listarTodos();
	
	@Insert("INSERT INTO tb_medicamento(nombre) VALUES(#{nombre})")
	@SelectKey( statement="SELECT LAST_INSERT_ID() as medicamentoId", keyProperty="medicamentoId", keyColumn = "medicamento_id", 
	resultType = int.class, before = false )
	void registrarMedicamento( Medicamento medicamento );
		
	@Results( value = {
		@Result( property = "medicamentoId", column = "medicamento_id")
	})
	@Select( "SELECT * FROM tb_medicamento M WHERE M.estado = 1"
			+ " AND M.nombre like concat('%',#{query},'%')"
			+ " ORDER BY M.medicamento_id LIMIT #{inicio},#{fin}" )
	List<Medicamento> listarPaginado( Integer inicio, Integer fin, String query );
	
	@Select("SELECT COUNT(M.medicamento_id) as total_filas FROM tb_medicamento M"
			+ " WHERE M.estado = 1 AND M.nombre like concat('%',#{query},'%')")
	Integer contarTotalFilas( String query );
	
	@Results( value = {
		@Result( property = "medicamentoId", column = "medicamento_id")
	})
	@Select( "SELECT * FROM tb_medicamento M WHERE M.medicamento_id = #{medicamentoId} AND M.estado = 1;" )	
	Medicamento buscarPorMedicamentoId( int medicamentoId );
		
	@Update("UPDATE tb_medicamento SET nombre = #{nombre} WHERE medicamento_id = #{medicamentoId}")
	void actualizarMedicamento( Medicamento medicamento );
	
	@Update("UPDATE tb_medicamento SET estado = 0 WHERE medicamento_id = #{medicamentoId}")
	void eliminarMedicamento( Integer medicamentoId );
}
