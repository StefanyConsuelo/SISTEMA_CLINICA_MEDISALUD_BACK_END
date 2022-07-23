package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sidet.idat.ws.medisalud.entity.AreaMedica;

@Mapper
public interface AreaMedicaRepository {

	@Results( value = {
		@Result(property = "areaMedicaId", column = "area_medica_id")
	})
	@Select("SELECT area_medica_id, nombre FROM tb_area_medica AM WHERE AM.estado = 1 ORDER BY AM.nombre ASC;")
	List<AreaMedica> listarTodos();
	
	@Insert("INSERT INTO tb_area_medica(nombre) VALUES(#{nombre})")
	@SelectKey( statement="SELECT LAST_INSERT_ID() as areaMedicaId", keyProperty="areaMedicaId", keyColumn = "area_medica_id", 
	resultType = int.class, before = false )
	void registrarMedicamento( AreaMedica areaMedica );
		
	@Results( value = {
		@Result(property = "areaMedicaId", column = "area_medica_id")
	})
	@Select( "SELECT * FROM tb_area_medica AM WHERE AM.estado = 1"
			+ " AND AM.nombre like concat('%',#{query},'%')"
			+ " ORDER BY AM.area_medica_id LIMIT #{inicio},#{fin}" )
	List<AreaMedica> listarPaginado( Integer inicio, Integer fin, String query );
	
	@Select("SELECT COUNT(AM.area_medica_id) as total_filas FROM tb_area_medica AM"
			+ " WHERE AM.estado = 1 AND AM.nombre like concat('%',#{query},'%')")
	Integer contarTotalFilas( String query );
	
	@Results( value = {
		@Result(property = "areaMedicaId", column = "area_medica_id")
	})
	@Select( "SELECT * FROM tb_area_medica AM WHERE AM.area_medica_id = #{areaMedicaId} AND AM.estado = 1;" )	
	AreaMedica buscarPorAreaMedicaId( int areaMedicaId );
		
	@Update("UPDATE tb_area_medica SET nombre = #{nombre} WHERE area_medica_id = #{areaMedicaId}")
	void actualizarAreaMedica( AreaMedica areaMedica );
	
	@Update("UPDATE tb_area_medica SET estado = 0 WHERE area_medica_id = #{areaMedicaId}")
	void eliminarMedicamento( Integer areaMedicaId );
		
}
