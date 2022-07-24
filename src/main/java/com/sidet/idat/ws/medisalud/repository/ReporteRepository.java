package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sidet.idat.ws.medisalud.entity.dto.ReporteDTO;

@Mapper
public interface ReporteRepository {

	@Results( value = {
		@Result(property = "mes", column = "mes_registro"),
		@Result(property = "cantidad", column = "cantidad_paciente")
	})
	@Select( "SELECT MONTH(P.fecha_registro) AS mes_registro, COUNT(P.paciente_id) AS cantidad_paciente FROM tb_paciente P "
			 + "WHERE YEAR(P.fecha_registro) = #{year} GROUP BY MONTH(P.fecha_registro);" )
	List<ReporteDTO> reportePacientesRegistradosAnualmente( int year );
	
	@Results( value = {
		@Result(property = "etiqueta", column = "canal"),
		@Result(property = "cantidad", column = "cantidad_paciente"),
	})
	@Select( "select P.canal, count(P.paciente_id) AS cantidad_paciente FROM tb_paciente P WHERE YEAR(P.fecha_registro) = #{year} GROUP BY P.canal;" )
	List<ReporteDTO> reportePacientesRecomendadosCanalAnualmente( int year );
	
	@Results( value = {
		@Result(property = "mes", column = "mes_cita"),
		@Result(property = "cantidad", column = "cantidad_cita")
	})
	@Select( "SELECT MONTH(CM.fecha_cita) AS mes_cita, COUNT(CM.paciente_id) AS cantidad_cita FROM tb_cita_medica CM "
			 + "WHERE YEAR(CM.fecha_cita) = #{year} GROUP BY MONTH(CM.fecha_cita);" )
	List<ReporteDTO> reporteCitasGeneradasAnualmente( int year );
	
	@Results( value = {
		@Result(property = "mes", column = "mes_registro"),
		@Result(property = "cantidad", column = "cantidad_hospitalizaciones")
	})
	@Select( "SELECT MONTH(H.fecha_registro) AS mes_registro, COUNT(H.hospitalizacion_id) AS cantidad_hospitalizaciones "
			 + "FROM tb_hospitalizacion H WHERE YEAR(H.fecha_registro) = #{year} GROUP BY MONTH(H.fecha_registro);" )
	List<ReporteDTO> reportePacientesHospitalizadosAnualmente( int year );
	
	@Select(" select ts.nombre as 'etiqueta', count(tcm.cita_medica_id) as 'cantidad' from tb_cita_medica tcm" + 
			" inner join tb_servicio ts on tcm.servicio_id = ts.servicio_id" + 
			" where year(fecha_cita) = #{year} and ts.area_medica_id = #{areaMedicaId}" + 
			" group by ts.nombre")
	List<ReporteDTO> reporteServiciosMasUtilizadosCitasAnualmente( Integer year, Integer areaMedicaId);
	
	@Select("select count(*) as 'cantidad' from tb_paciente tp where tp.estado = 1")
	Integer reporteCantidadTotalPacientesAnualmente();
	
	@Select("select count(*) as 'cantidad' from tb_cita_medica tcm where year(tcm.fecha_cita) = year(current_date())")
	Integer reporteCantidadTotalCitasAnualmente();
	
	@Select("select count(*) as 'cantidad' from tb_hospitalizacion th where year(th.fecha_registro) = year(current_date())")
	Integer reporteCantidadTotalHospitalizacionAnualmente();
	
	@Select("select count(*) as 'cantidad' from tb_personal_medico tpm where estado = 1")
	Integer reporteCantidadTotalPersonalMedicoAnualmente();
}
