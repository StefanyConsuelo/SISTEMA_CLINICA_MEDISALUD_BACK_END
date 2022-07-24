package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.sidet.idat.ws.medisalud.entity.MotivoConsulta;

@Mapper
public interface MotivoConsultaRepository {

	@Insert(" INSERT INTO tb_motivo_consulta (tipo_enfermedad, sintomas_principales, relato, apetito, sedantes, orina, historia_clinica_id)" + 
			" VALUES(#{tipoEnfermedad}, #{sintomasPrincipales}, #{relato}, #{apetito}, #{sedantes}, #{orina}, #{historiaClinica.historiaClinicaId})")
	@SelectKey( statement="SELECT LAST_INSERT_ID() as motivo_consulta_id", keyProperty="motivoConsultaId", 
		keyColumn = "motivo_consulta_id", resultType = int.class, before = false )
	void registrar( MotivoConsulta motivoConsulta );

	@Results( value = {
		@Result(property = "motivoConsultaId", column = "motivo_consulta_id"),
		@Result(property = "tipoEnfermedad", column = "tipo_enfermedad"),
		@Result(property = "sintomasPrincipales", column = "sintomas_principales")
	})
	@Select(" select * from tb_motivo_consulta tmc" + 
			" where tmc.historia_clinica_id = #{historiaClinicaId}" + 
			" order by tmc.motivo_consulta_id desc LIMIT #{inicio},#{fin};" )
	List<MotivoConsulta> listarPaginado( Integer inicio, Integer fin, Integer historiaClinicaId );
	
	@Select("SELECT COUNT(*) as total_filas FROM tb_motivo_consulta"
			+ " where historia_clinica_id = #{historiaClinicaId}")
	Integer contarTotalFilas( Integer historiaClinicaId );
}
