package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sidet.idat.ws.medisalud.entity.Servicio;

@Mapper
public interface ServicioRepository {

	@Results( value = {
		@Result( property = "servicioId", column = "servicio_id" )
	})
	@Select("SELECT S.servicio_id, S.nombre FROM tb_servicio S where S.estado = 1 and S.area_medica_id = #{areaMedicaId} ORDER BY S.nombre ASC;")
	List<Servicio> listarPorAreaMedicaId( Integer areaMedicaId );
	
	@Insert("INSERT INTO tb_servicio(nombre, area_medica_id) VALUES(#{nombre}, #{areaMedica.areaMedicaId})")
	@SelectKey( statement="SELECT LAST_INSERT_ID() as servicioId", keyProperty="servicioId", keyColumn = "servicio_id", 
	resultType = int.class, before = false )
	void registrarServicio( Servicio servicio );
		
	@Results( value = {
		@Result( property = "servicioId", column = "servicio_id" ),
		@Result( property = "nombre", column = "servicio" ),
		@Result( property = "areaMedica.nombre", column = "area_medica" ),
		@Result( property = "areaMedica.areaMedicaId", column = "area_medica_id" )
	})
	@Select( "SELECT "
			+ " s.servicio_id, s.nombre as servicio, a.nombre as area_medica, s.area_medica_id as area_medica_id"
			+ " FROM tb_servicio S "
			+ " INNER JOIN tb_area_medica a ON s.area_medica_id = a.area_medica_id"
			+ " WHERE S.estado = 1"
			+ " AND S.nombre like concat('%',#{query},'%')"
			+ " ORDER BY S.servicio_id LIMIT #{inicio},#{fin}" )
	List<Servicio> listarPaginado( Integer inicio, Integer fin, String query );
	
	@Select("SELECT COUNT(S.servicio_id) as total_filas FROM tb_servicio S"
			+ " WHERE S.estado = 1 AND S.nombre like concat('%',#{query},'%')")
	Integer contarTotalFilas( String query );
	
	@Results( value = {
		@Result( property = "servicioId", column = "servicio_id" )
	})
	@Select( "SELECT * FROM tb_servicio S WHERE S.servicio_id = #{servicioId} AND S.estado = 1;" )	
	Servicio buscarPorServicioId( int servicioId );
		
	@Update("UPDATE tb_servicio SET nombre = #{nombre}, area_medica_id = #{areaMedica.areaMedicaId} WHERE servicio_id = #{servicioId}")
	void actualizarServicio( Servicio servicio );
	
	@Update("UPDATE tb_servicio SET estado = 0 WHERE servicio_id = #{servicioId}")
	void eliminarServicio( Integer servicioId );
	
}
