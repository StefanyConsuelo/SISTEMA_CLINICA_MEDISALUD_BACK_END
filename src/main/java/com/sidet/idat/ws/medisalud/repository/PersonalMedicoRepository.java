package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.sidet.idat.ws.medisalud.entity.PersonalMedico;

@Mapper
public interface PersonalMedicoRepository {
	
	@Insert("insert into tb_persona(nombres,apellido_paterno,apellido_materno,tipo_documento,numero_documento,"
			+ "fecha_nacimiento,telefono_fijo,celular,correo) values(#{nombres},#{apellidoPaterno},"
			+ "#{apellidoMaterno},#{tipoDocumento},#{numeroDocumento},#{fechaNacimiento},#{telefonoFijo},"
			+ "#{celular},#{correo})")
	@SelectKey(statement="SELECT LAST_INSERT_ID() as personaId", keyProperty="personaId", keyColumn = "persona_id", resultType = int.class, before = false )
	void registrarPersona(PersonalMedico persona);
	
	@Insert("insert into tb_personal_medico(profesion,documento_profesional,"
			+ "numero_documento_profesional,estado_laboral,persona_id,servicio_id)"
			+ "values(#{profesion},#{documentoProfesional},#{numeroDocumentoProfesional}"
			+ ",#{estadoLaboral},#{personaId},#{servicio.servicioId})")
	@SelectKey(statement="SELECT LAST_INSERT_ID() as personalMedicoId", keyProperty="personalMedicoId", keyColumn = "personal_medico_id", resultType = int.class, before = false )
	void registrarPersonalMedico(PersonalMedico medico);
	
	@Insert("INSERT INTO tb_horario_medico(personal_medico_id, tm_lunes, tm_martes, tm_miercoles, tm_jueves, tm_viernes," 
			+ " tm_sabado, tm_domingo, tt_lunes, tt_martes, tt_miercoles, tt_jueves, tt_viernes, tt_sabado, tt_domingo,"
			+ " tn_lunes, tn_martes, tn_miercoles, tn_jueves, tn_viernes, tn_sabado, tn_domingo)"
			+ " VALUES(#{id}, '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-')")
	void registrarHorarioMedico(Integer id);
	
	@Results(value = {
			@Result(property = "personaId", column = "persona_id"),
			@Result(property = "nombres", column = "nombres"),
			@Result(property = "apellidoPaterno", column = "apellido_paterno"),
			@Result(property = "apellidoMaterno", column = "apellido_materno"),
			@Result(property = "tipoDocumento", column = "tipo_documento"),
			@Result(property = "numeroDocumento", column = "numero_documento"),
			@Result(property = "fechaNacimiento", column = "fecha_nacimiento"),
			@Result(property = "telefonoFijo", column = "telefono_fijo"),
			@Result(property = "celular", column = "celular"),
			@Result(property = "correo", column = "correo"),
			@Result(property = "personalMedicoId", column = "personal_medico_id"),
			@Result(property = "profesion", column = "profesion"),
			@Result(property = "documentoProfesional", column = "documento_profesional"),
			@Result(property = "numeroDocumentoProfesional", column = "numero_documento_profesional"),
			@Result(property = "estadoLaboral", column = "estado_laboral"),
			@Result(property = "servicio.servicioId", column = "servicio_id"),
			@Result(property = "servicio.nombre", column = "nombre"),
			@Result(property = "servicio.areaMedica.areaMedicaId", column = "area_medica_id")
	}, id = "resultValue1")
	@Select("select * from tb_personal_medico tbpm inner join tb_persona tbp"
			+ " on tbpm.persona_id = tbp.persona_id inner join tb_servicio ts on ts.servicio_id = tbpm.servicio_id where tbpm.estado = 1"
			+ " and tbp.apellido_paterno like concat('%',#{query},'%') ORDER BY tbpm.fecha_registro limit #{inicio},#{fin}")
	List<PersonalMedico> listarPaginado(Integer inicio, Integer fin, String query);
	
	@Select("select count(PM.personal_medico_id) as total_filas from tb_personal_medico PM INNER JOIN tb_persona Per ON Per.persona_id = PM.persona_id"
			+ " WHERE PM.estado = 1 AND Per.apellido_paterno like concat('%',#{query},'%')")
	Integer contarTotalFilas( String query );
	
	@ResultMap("resultValue1")
	@Select("select * from tb_personal_medico tbpm inner join tb_persona tbp"
			+ " on tbpm.persona_id = tbp.persona_id inner join tb_servicio ts on ts.servicio_id = tbpm.servicio_id where tbpm.personal_medico_id = #{id} and tbpm.estado = 1")
	PersonalMedico buscarPodId(Integer id);
	
	@Insert("update tb_personal_medico set estado = 0 where personal_medico_id=#{id}")
	void eliminar(Integer Id);
	
	@Select("update tb_personal_medico tbpm inner join tb_persona tbp on tbpm.persona_id = tbp.persona_id"
			+ " set tbpm.profesion = #{profesion}, tbpm.documento_profesional = #{documentoProfesional},"
			+ " tbpm.numero_documento_profecional = #{numeroDocumentoProfesional}, tbpm.estado_laboral = #{estadoLaboral},"
			+ " tbpm.servicio_id = #{servicio.servicioId}, tbp.telefono_fijo = #{telefonoFijo}, tbp.celular = #{celular}, tbp.correo = #{correo}"
			+ " where tbpm.personal_medico_id = #{personalMedicoId}")
	void actualizar(PersonalMedico medico);
	
	@ResultMap("resultValue1")
	@Select("select * from tb_personal_medico tbpm inner join tb_persona tbp on tbpm.persona_id = tbp.persona_id where tbp.numero_documento = #{dni} and tbpm.estado = 1")
	PersonalMedico validaDniExistente(String dni);
	
	@ResultMap("resultValue1")
	@Select("select tpm.personal_medico_id, tp.nombres, tp.apellido_paterno, tp.apellido_materno, tp.persona_id from tb_persona tp " + 
			"inner join tb_personal_medico tpm on tp.persona_id = tpm.persona_id " + 
			"where tpm.estado = 1;")
	List<PersonalMedico> listarTodos();
	
	@ResultMap("resultValue1")
	@Select("select * from tb_personal_medico tbpm inner join tb_persona tbp"
			+ " on tbpm.persona_id = tbp.persona_id inner join tb_servicio ts on ts.servicio_id = tbpm.servicio_id where tbpm.persona_id = #{personaId} and tbpm.estado = 1")
	PersonalMedico buscarPorPersonaId(Integer personaId);
}
