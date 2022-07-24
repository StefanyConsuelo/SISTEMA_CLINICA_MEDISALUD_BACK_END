package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sidet.idat.ws.medisalud.entity.HorarioMedico;

@Mapper
public interface HorarioMedicoRepository {
	
	@Results(value = {
		@Result(property = "horarioMedicoId", column = "horario_medico_id"),
		@Result(property = "personalMedico.personalMedicoId", column = "personal_medico_id"),
		@Result(property = "tmLunes", column = "tm_lunes"),
		@Result(property = "tmMartes", column = "tm_martes"),
		@Result(property = "tmMiercoles", column = "tm_miercoles"),
		@Result(property = "tmJueves", column = "tm_jueves"),
		@Result(property = "tmViernes", column = "tm_viernes"),
		@Result(property = "tmSabado", column = "tm_sabado"),
		@Result(property = "tmDomingo", column = "tm_domingo"),
		@Result(property = "ttLunes", column = "tt_lunes"),
		@Result(property = "ttMartes", column = "tt_martes"),
		@Result(property = "ttMiercoles", column = "tt_miercoles"),
		@Result(property = "ttJueves", column = "tt_jueves"),
		@Result(property = "ttViernes", column = "tt_viernes"),
		@Result(property = "ttSabado", column = "tt_sabado"),
		@Result(property = "ttDomingo", column = "tt_domingo"),
		@Result(property = "tnLunes", column = "tn_lunes"),
		@Result(property = "tnMartes", column = "tn_martes"),
		@Result(property = "tnMiercoles", column = "tn_miercoles"),
		@Result(property = "tnJueves", column = "tn_jueves"),
		@Result(property = "tnViernes", column = "tn_viernes"),
		@Result(property = "tnSabado", column = "tn_sabado"),
		@Result(property = "tnDomingo", column = "tn_domingo")
	}, id = "ResulyHorarioMedico")
	@Select("select * from tb_horario_medico thm left join tb_personal_medico tpm on thm.personal_medico_id = tpm.personal_medico_id where tpm.estado=1")
	List<HorarioMedico> listarTodos();

	@Update("UPDATE tb_horario_medico tbh inner join tb_personal_medico tbpm on tbh.personal_medico_id = tbpm.personal_medico_id"
			+ " SET tm_lunes=#{tmLunes}, tm_martes=#{tmMartes}, tm_miercoles=#{tmMiercoles}, tm_jueves=#{tmJueves}, tm_viernes=#{tmViernes},"
			+ " tm_sabado=#{tmSabado}, tm_domingo=#{tmDomingo}, tt_lunes=#{ttLunes}, tt_martes=#{ttMartes}, tt_miercoles=#{ttMiercoles},"
			+ " tt_jueves=#{ttJueves}, tt_viernes=#{ttViernes}, tt_sabado=#{ttSabado}, tt_domingo=#{ttDomingo}, tn_lunes=#{tnLunes},"
			+ " tn_martes=#{tnMartes}, tn_miercoles=#{tnMiercoles}, tn_jueves=#{tnJueves}, tn_viernes=#{tnViernes}, tn_sabado=#{tnSabado}, tn_domingo=#{tnDomingo}"
			+ " WHERE tbpm.personal_medico_id = #{personalMedico.personalMedicoId} AND tbh.horario_medico_id = #{horarioMedicoId}")
	void actualizar(HorarioMedico horarioMedico);
	

	@ResultMap("ResulyHorarioMedico")
	@Select("select * from tb_horario_medico thm left join tb_personal_medico"
			+ " tpm on thm.personal_medico_id = tpm.personal_medico_id where thm.personal_medico_id = #{id} and tpm.estado=1")
	HorarioMedico buscarPorId(Integer id);

	@Results( value = {
		@Result(property = "personalMedico.personalMedicoId", column = "personal_medico_id"),
		@Result(property = "personalMedico.nombres", column = "nombres"),
		@Result(property = "personalMedico.apellidoPaterno", column = "apellido_paterno"),
		@Result(property = "personalMedico.apellidoMaterno", column = "apellido_materno"),
		@Result(property = "personalMedico.servicio.servicioId", column = "servicio_id"),
		@Result(property = "personalMedico.servicio.nombre", column = "nombre_servicio"),
		@Result(property = "horarioMedicoId", column = "horario_medico_id"),
		@Result(property = "tmLunes", column = "tm_lunes"),
		@Result(property = "tmMartes", column = "tm_martes"),
		@Result(property = "tmMiercoles", column = "tm_miercoles"),
		@Result(property = "tmJueves", column = "tm_jueves"),
		@Result(property = "tmViernes", column = "tm_viernes"),
		@Result(property = "tmSabado", column = "tm_sabado"),
		@Result(property = "tmDomingo", column = "tm_domingo"),
		@Result(property = "ttLunes", column = "tt_lunes"),
		@Result(property = "ttMartes", column = "tt_martes"),
		@Result(property = "ttMiercoles", column = "tt_miercoles"),
		@Result(property = "ttJueves", column = "tt_jueves"),
		@Result(property = "ttViernes", column = "tt_viernes"),
		@Result(property = "ttSabado", column = "tt_sabado"),
		@Result(property = "ttDomingo", column = "tt_domingo"),
		@Result(property = "tnLunes", column = "tn_lunes"),
		@Result(property = "tnMartes", column = "tn_martes"),
		@Result(property = "tnMiercoles", column = "tn_miercoles"),
		@Result(property = "tnJueves", column = "tn_jueves"),
		@Result(property = "tnViernes", column = "tn_viernes"),
		@Result(property = "tnSabado", column = "tn_sabado"),
		@Result(property = "tnDomingo", column = "tn_domingo")
	})
	@Select("SELECT PM.personal_medico_id, P.nombres, P.apellido_paterno, P.apellido_materno, S.servicio_id, S.nombre as nombre_servicio, "+
			"HM.horario_medico_id, HM.tm_lunes, HM.tm_martes, HM.tm_miercoles, HM.tm_jueves, HM.tm_viernes, HM.tm_sabado, HM.tm_domingo, "+
			"HM.tt_lunes, HM.tt_martes, HM.tt_miercoles, HM.tt_jueves, HM.tt_viernes, HM.tt_sabado, HM.tt_domingo, "+
			"HM.tn_lunes, HM.tn_martes, HM.tn_miercoles, HM.tn_jueves, HM.tn_viernes, HM.tn_sabado, HM.tn_domingo "+
			"FROM tb_horario_medico HM "+
			"INNER JOIN tb_personal_medico PM ON PM.personal_medico_id = HM.personal_medico_id "+
			"INNER JOIN tb_servicio S ON S.servicio_id = PM.servicio_id "+
			"INNER JOIN tb_persona P ON P.persona_id = PM.persona_id "+
			"WHERE S.servicio_id = #{servicioId} AND PM.estado = 1 ORDER BY P.apellido_paterno ASC;")
	List<HorarioMedico> listarPorServicioId( Integer servicioId );

}
