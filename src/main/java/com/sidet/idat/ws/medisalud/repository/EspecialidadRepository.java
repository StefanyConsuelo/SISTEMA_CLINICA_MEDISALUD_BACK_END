package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.sidet.idat.ws.medisalud.entity.Especialidad;

@Mapper
public interface EspecialidadRepository {
	
	@Results(value = {
			@Result( property = "especialidadId", column = "especialidad_id" ),
			@Result( property = "especialidad", column = "especialidad" )
	}, id = "resultEspecialidadV1")
	@Select("select * from tb_especialidad where estado=1")
	List<Especialidad> listarTodos();
	
	@Insert("insert into tb_especialidad(especialidad) values(#{especialidad})")
	@SelectKey(statement="SELECT LAST_INSERT_ID() as especialidadId", keyProperty="especialidadId", keyColumn = "especialidad_id", resultType = int.class, before = false )
	void registrar(Especialidad especialidad);
	
	@Insert("update tb_especialidad set especialidad=#{especialidad} where especialidad_id=#{especialidadId}")
	void actualizar(Especialidad especialidad);
	
	@ResultMap("resultEspecialidadV1")
	@Select("select * from tb_especialidad where especialidad_id=#{especialidadId} and estado = 1")
	Especialidad buscarPorId(Integer especialidadId);
	
	@Select("select count(especialidad_id) as total_filas from tb_especialidad")
	Integer contarTotalFilas();
	
	@ResultMap("resultEspecialidadV1")
	@Select("select * from tb_especialidad order by especialidad_id limit #{inicio},#{fin}")
	List<Especialidad> listarPaginado(Integer inicio, Integer fin);
	
	@Insert("update tb_especialidad set estado=0 where especialidad_id=#{especialidadId}")
	void eliminar(Integer especialidadId);

}
