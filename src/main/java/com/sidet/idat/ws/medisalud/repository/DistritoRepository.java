package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sidet.idat.ws.medisalud.entity.Distrito;

@Mapper
public interface DistritoRepository {
	
	@Results(value = {
		@Result(property = "distritoId", column = "distrito_id")
	}, id = "resultDistritoV1" )
	@Select("SELECT D.distrito_id, D.nombre FROM tb_distrito D WHERE D.provincia_id = #{provinciaId};")
	List<Distrito> listarPorProvinciaId( Integer provinciaId );
	
	@ResultMap("resultDistritoV1")
	@Select("SELECT D.distrito_id FROM tb_distrito D WHERE D.distrito_id = #{distritoId};")
	Distrito buscarPorId( Integer distritoId );

}
