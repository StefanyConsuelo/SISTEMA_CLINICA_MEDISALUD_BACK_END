package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sidet.idat.ws.medisalud.entity.Provincia;

@Mapper
public interface ProvinciaRepository {

	@Results( value = {
		@Result( property = "provinciaId", column = "provincia_id" )
	})
	@Select("SELECT P.provincia_id, P.nombre FROM tb_provincia P WHERE P.departamento_id = #{departamentoId};")
	List<Provincia> listarPorDepartamentoId( Integer departamentoId );
	
}
