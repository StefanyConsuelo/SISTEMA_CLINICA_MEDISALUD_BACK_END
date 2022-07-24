package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sidet.idat.ws.medisalud.entity.Perfil;

@Mapper
public interface PerfilRepository {

	@Results( value = {
		@Result( property = "perfilId", column = "perfil_id")
	})
	@Select("SELECT perfil_id, nombre FROM tb_perfil;")
	List<Perfil> listarTodos();
	
}
