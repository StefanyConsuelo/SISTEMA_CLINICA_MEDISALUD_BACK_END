package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.sidet.idat.ws.medisalud.entity.HistoriaClinica;

@Mapper
public interface HistoriaClinicaRepository {

	@Insert("INSERT INTO tb_historia_clinica (paciente_id) VALUES(#{paciente.pacienteId});")
	@SelectKey( statement="SELECT LAST_INSERT_ID() as historiaClinicaId", keyProperty="historiaClinicaId", 
		keyColumn = "historia_clinica_id", resultType = int.class, before = false )
	void registrar( HistoriaClinica historiaClinica );
	
	@Results( value = {
		@Result(property = "historiaClinicaId", column = "historia_clinica_id"),
		@Result(property = "paciente.pacienteId", column = "paciente_id"),
		@Result(property = "paciente.nombres", column = "nombres"),
		@Result(property = "paciente.apellidoPaterno", column = "apellido_paterno"),
		@Result(property = "paciente.apellidoMaterno", column = "apellido_materno"),
		@Result(property = "paciente.numeroDocumento", column = "numero_documento"),
		@Result(property = "paciente.tipoDocumento", column = "tipo_documento")
	})
	@Select("select thc.*, tp2.nombres, tp2.apellido_paterno, tp2.apellido_materno," + 
			" tp2.tipo_documento, tp2.numero_documento" + 
			" from tb_historia_clinica thc " + 
			" inner join tb_paciente tp on tp.paciente_id = thc.paciente_id" + 
			" inner join tb_persona tp2 on tp2.persona_id = tp.persona_id" +
			" where tp.estado = 1 AND tp2.numero_documento LIKE concat('%',#{query},'%') "+
			" ORDER BY thc.fecha_registro LIMIT #{inicio},#{fin}" )
	List<HistoriaClinica> listarPaginado( Integer inicio, Integer fin, String query );
	
	@Select("select count(*) as total_filas from tb_historia_clinica thc " + 
			" inner join tb_paciente tp on tp.paciente_id = thc.paciente_id" + 
			" inner join tb_persona tp2 on tp2.persona_id = tp.persona_id" +
			" where tp.estado = 1 AND tp2.numero_documento LIKE concat('%',#{query},'%') ")
	Integer contarTotalFilas( String query);
}
