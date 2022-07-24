package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sidet.idat.ws.medisalud.entity.Paciente;
import com.sidet.idat.ws.medisalud.entity.dto.TriajePacienteDTO;

@Mapper
public interface PacienteRepository {
	
	@Insert( "INSERT INTO tb_persona( nombres, apellido_paterno, apellido_materno, tipo_documento, numero_documento, "
			+ "fecha_nacimiento, telefono_fijo, celular, correo ) "
			+ "VALUES( #{nombres}, #{apellidoPaterno}, #{apellidoMaterno}, #{tipoDocumento}, #{numeroDocumento}, "
			+ "#{fechaNacimiento}, #{telefonoFijo}, #{celular}, #{correo} )" )
	@SelectKey( statement="SELECT LAST_INSERT_ID() as personaId", keyProperty="personaId", keyColumn = "persona_id", 
				resultType = int.class, before = false )
	void registrarPersona( Paciente persona );
	
	@Insert( "INSERT INTO tb_paciente( genero, estado_civil, grupo_sanguineo, canal, religion, lugar_nacimiento, "
			+ "tipo_paciente, direccion, responsable_nombres_apellidos, responsable_parentesco_paciente, "
			+ "responsable_telefono, distrito_id, persona_id )"
			+ "VALUES( #{genero}, #{estadoCivil}, #{grupoSanguineo}, #{canal}, #{religion}, #{lugarNacimiento}, "
			+ "#{tipoPaciente}, #{direccion}, #{responsableNombresApellidos}, #{responsableParentescoPaciente}, "
			+ "#{responsableTelefono}, #{distrito.distritoId}, #{personaId} )" )
	@SelectKey( statement="SELECT LAST_INSERT_ID() as pacienteId", keyProperty="pacienteId", 
				keyColumn = "paciente_id", resultType = int.class, before = false )
	void registrarPaciente( Paciente paciente );
	
	@Results( value = {
		@Result(property = "personaId", column = "persona_id"),
		@Result(property = "apellidoPaterno", column = "apellido_paterno"),
		@Result(property = "apellidoMaterno", column = "apellido_materno"),
		@Result(property = "tipoDocumento", column = "tipo_documento"),
		@Result(property = "numeroDocumento", column = "numero_documento"),
		@Result(property = "pacienteId", column = "paciente_id")
	})
	@Select( "SELECT Per.persona_id, Per.nombres, Per.apellido_paterno, Per.apellido_materno, Per.tipo_documento, "
			+ "Per.numero_documento, Per.celular, P.paciente_id FROM tb_paciente P "
			+ "INNER JOIN tb_persona Per ON Per.persona_id = P.persona_id WHERE P.estado = 1 "
			+ "AND Per.numero_documento like concat('%',#{query},'%')"
			+ "ORDER BY P.fecha_registro LIMIT #{inicio},#{fin}" )
	List<Paciente> listarPaginado( Integer inicio, Integer fin, String query );
	
	@Select("SELECT COUNT(P.paciente_id) as total_filas FROM tb_paciente P "
			+ " INNER JOIN tb_persona Per ON Per.persona_id = P.persona_id "
			+ " WHERE P.estado = 1 AND Per.numero_documento like concat('%',#{query},'%')")
	Integer contarTotalFilas(String query );
	
	@Results( value = {
		@Result(property = "personaId", column = "persona_id"),
		@Result(property = "apellidoPaterno", column = "apellido_paterno"),
		@Result(property = "apellidoMaterno", column = "apellido_materno"),
		@Result(property = "tipoDocumento", column = "tipo_documento"),
		@Result(property = "numeroDocumento", column = "numero_documento"),
		@Result(property = "fechaNacimiento", column = "fecha_nacimiento"),
		@Result(property = "telefonoFijo", column = "telefono_fijo"),
		@Result(property = "pacienteId", column = "paciente_id"),
		@Result(property = "estadoCivil", column = "estado_civil"),
		@Result(property = "grupoSanguineo", column = "grupo_sanguineo"),
		@Result(property = "lugarNacimiento", column = "lugar_nacimiento"),
		@Result(property = "tipoPaciente", column = "tipo_paciente"),
		@Result(property = "responsableNombresApellidos", column = "responsable_nombres_apellidos"),
		@Result(property = "responsableParentescoPaciente", column = "responsable_parentesco_paciente"),
		@Result(property = "responsableTelefono", column = "responsable_telefono"),
		@Result(property = "distrito.provincia.departamento.departamentoId", column = "departamento_id"),
		@Result(property = "distrito.provincia.departamento.nombre", column = "nombre_departamento"),
		@Result(property = "distrito.provincia.provinciaId", column = "provincia_id"),
		@Result(property = "distrito.provincia.nombre", column = "nombre_provincia"),
		@Result(property = "distrito.distritoId", column = "distrito_id"),
		@Result(property = "distrito.nombre", column = "nombre_distrito")
	}, id = "resultPacienteV1" )
	@Select( "SELECT Per.persona_id, Per.nombres, Per.apellido_paterno, Per.apellido_materno, Per.tipo_documento, "
			+ "Per.numero_documento, Per.fecha_nacimiento, Per.telefono_fijo, Per.celular, Per.correo, "
			+ "P.paciente_id, P.genero, P.estado_civil, P.grupo_sanguineo, P.canal, P.religion, P.lugar_nacimiento, "
			+ "P.tipo_paciente, P.direccion, P.responsable_nombres_apellidos, P.responsable_parentesco_paciente, "
			+ "P.responsable_telefono, D.departamento_id, D.nombre as nombre_departamento, Pro.provincia_id, "
			+ "Pro.nombre as nombre_provincia, Dis.distrito_id, Dis.nombre as nombre_distrito FROM tb_paciente P "
			+ "INNER JOIN tb_persona Per ON Per.persona_id = P.persona_id "
			+ "INNER JOIN tb_distrito Dis ON Dis.distrito_id = P.distrito_id "
			+ "INNER JOIN tb_provincia Pro ON Pro.provincia_id = Dis.provincia_id "
			+ "INNER JOIN tb_departamento D ON D.departamento_id = Pro.departamento_id "
			+ "WHERE Per.numero_documento = #{numeroDocumento} AND P.estado = 1 AND Per.tipo_documento = #{tipoDocumento};" )	
	Paciente buscarPorNumeroDocumento( String numeroDocumento, String tipoDocumento );
	
	@ResultMap("resultPacienteV1")
	@Select( "SELECT Per.persona_id, Per.nombres, Per.apellido_paterno, Per.apellido_materno, Per.tipo_documento, "
			+ "Per.numero_documento, Per.fecha_nacimiento, Per.telefono_fijo, Per.celular, Per.correo, "
			+ "P.paciente_id, P.genero, P.estado_civil, P.grupo_sanguineo, P.canal, P.religion, P.lugar_nacimiento, "
			+ "P.tipo_paciente, P.direccion, P.responsable_nombres_apellidos, P.responsable_parentesco_paciente, "
			+ "P.responsable_telefono, D.departamento_id, D.nombre as nombre_departamento, Pro.provincia_id, "
			+ "Pro.nombre as nombre_provincia, Dis.distrito_id, Dis.nombre as nombre_distrito FROM tb_paciente P "
			+ "INNER JOIN tb_persona Per ON Per.persona_id = P.persona_id "
			+ "INNER JOIN tb_distrito Dis ON Dis.distrito_id = P.distrito_id "
			+ "INNER JOIN tb_provincia Pro ON Pro.provincia_id = Dis.provincia_id "
			+ "INNER JOIN tb_departamento D ON D.departamento_id = Pro.departamento_id "
			+ "WHERE P.paciente_id = #{pacienteId} AND P.estado = 1;" )	
	Paciente buscarPorIdPaciente( Integer pacienteId );
	
	@Update( "UPDATE tb_persona SET nombres = #{nombres}, apellido_paterno = #{apellidoPaterno}, "
			+ "apellido_materno = #{apellidoMaterno}, tipo_documento = #{tipoDocumento}, "
			+ "numero_documento = #{numeroDocumento}, fecha_nacimiento = #{fechaNacimiento}, "
			+ "telefono_fijo = #{telefonoFijo}, celular = #{celular}, correo = #{correo} "
			+ "WHERE persona_id=#{personaId}" )
	void actualizarPersona( Paciente persona );
	
	@Update( "UPDATE tb_paciente SET genero = #{genero}, estado_civil = #{estadoCivil}, "
			+ "grupo_sanguineo = #{grupoSanguineo}, canal = #{canal}, religion = #{religion}, "
			+ "lugar_nacimiento = #{lugarNacimiento}, tipo_paciente = #{tipoPaciente}, "
			+ "distrito_id = #{distrito.distritoId}, direccion = #{direccion}, "
			+ "responsable_nombres_apellidos = #{responsableNombresApellidos}, "
			+ "responsable_parentesco_paciente = #{responsableParentescoPaciente}, "
			+ "responsable_telefono = #{responsableTelefono} "
			+ "WHERE paciente_id=#{pacienteId}" )
	void actualizarPaciente( Paciente paciente );
	
	@Delete( "UPDATE tb_paciente SET estado = 0 WHERE paciente_id=#{pacienteId}" )
	void eliminarPaciente( Integer pacienteId );
	
	@Update( "UPDATE tb_paciente SET peso = #{peso}, talla = #{talla}, fecha_actual_triaje = #{fechaActualTriaje} WHERE paciente_id=#{pacienteId}")
	void actualizarTriaje( Paciente paciente );
	
	@Results( value = {
			@Result(property = "fechaActualTriaje", column = "fecha_actual_triaje"),
		})
	@Select(  " select tp.peso, tp.talla, date_format(tp.fecha_actual_triaje,'%d-%m-%Y') as fecha_actual_triaje "
			+ " from tb_paciente tp"
			+ " where tp.paciente_id = #{pacienteId}")
	TriajePacienteDTO listarTriajeUltimo( Integer pacienteId );
	
	@Select(" select paciente_id from tb_historia_clinica where historia_clinica_id = #{idHistoriaClinica}")
	Integer obtenerIdPacientePor( Integer idHistoriaClinica );
}
