package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sidet.idat.ws.medisalud.entity.Examen;

@Mapper
public interface ExamenRespository {

	@Insert("INSERT INTO tb_examen (servicio_id, resultado, historia_clinica_id, personal_medico_id) "
			+ " VALUES(#{servicio.servicioId}, #{resultado}, #{historiaClinica.historiaClinicaId},"
			+ " #{personalMedico.personaId} );")
	@SelectKey( statement="SELECT LAST_INSERT_ID() as examenId", keyProperty="examenId", keyColumn = "examen_id", 
	resultType = int.class, before = false )
	void registrarExamen( Examen examen );
	
	@Results( value = {
		@Result(property = "examenId", column = "examen_id"),
		@Result(property = "servicio.nombre", column = "servicio"),
		@Result(property = "servicio.servicioId", column = "servicio_id"),
		@Result(property = "resultado", column = "resultado"),
		@Result(property = "historiaClinica.historiaClinicaId", column = "historia_clinica_id"),
		@Result(property = "personalMedico.nombres", column = "medico_nombres"),
		@Result(property = "personalMedico.apellidoPaterno", column = "medico_apellido_paterno"),
		@Result(property = "personalMedico.apellidoMaterno", column = "medico_apellido_materno"),
		@Result(property = "historiaClinica.paciente.nombres", column = "paciente_nombres"),
		@Result(property = "historiaClinica.paciente.apellidoPaterno", column = "paciente_apellido_paterno"),
		@Result(property = "historiaClinica.paciente.apellidoMaterno", column = "paciente_apellido_materno"),
		@Result(property = "historiaClinica.paciente.pacienteId", column = "paciente_id"),
		@Result(property = "estado", column = "estado"),
		@Result(property = "fechaCreacion", column = "fecha_creacion"),
		@Result(property = "servicio.areaMedica.areaMedicaId", column = "area_medica_id")
	})
	@Select("select te.examen_id, ts.nombre servicio, te.resultado, te.historia_clinica_id,"
			+ "	tp.nombres medico_nombres, tp.apellido_paterno medico_apellido_paterno,"
			+ "	tp.apellido_materno medico_apellido_materno, tp3.nombres paciente_nombres,"
			+ "	tp3.apellido_paterno paciente_apellido_paterno,"
			+ "	tp3.apellido_materno paciente_apellido_materno, te.estado,"
			+ "	thc.paciente_id, DATE_FORMAT(te.fecha_creacion,'%d-%m-%Y') as fecha_creacion,"
			+ " ts.area_medica_id, ts.servicio_id, tp2.paciente_id"
			+ " from tb_examen te"
			+ " inner join tb_servicio ts ON ts.servicio_id = te.servicio_id"
			+ " inner join tb_persona tp on tp.persona_id = te.personal_medico_id"
			+ " inner join tb_historia_clinica thc on thc.historia_clinica_id = te.historia_clinica_id"
			+ " inner join tb_paciente tp2 on tp2.paciente_id = thc.paciente_id"
			+ " inner join tb_persona tp3 on tp3.persona_id = tp2.persona_id"
			+ "	where te.historia_clinica_id  = #{historiaClinicaId} and (#{query} is null or #{query} = '' or te.examen_id = #{query})"
			+ "	order by te.examen_id desc limit #{inicio},#{fin};" )
	List<Examen> listarPaginado( Integer inicio, Integer fin, Integer historiaClinicaId, String query );
	
	@Select("SELECT COUNT(*) as total_filas FROM tb_examen"
			+ " where historia_clinica_id = #{historiaClinicaId} and (#{query} is null or #{query} = '' or examen_id = #{query})")
	Integer contarTotalFilas( Integer historiaClinicaId, String query );
	
	@Update("update tb_examen set resultado = #{resultado} where examen_id = #{examenId}")
	void editarResultado( Examen examen );
	
	@Delete("update tb_examen set estado = 'Finalizado' where examen_id = #{examenId}")
	void finalizarExamen( Integer examenId );
	
	@Delete("update tb_examen set estado = 'Cita generada' where examen_id = #{examenId}")
	void actualizarEstadoCitaGenerada( Integer examenId );
}
