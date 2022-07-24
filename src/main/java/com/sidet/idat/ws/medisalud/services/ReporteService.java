package com.sidet.idat.ws.medisalud.services;

import java.util.List;

import com.sidet.idat.ws.medisalud.entity.dto.ReporteDTO;

public interface ReporteService {

	List<ReporteDTO> reportePacientesRegistradosAnualmente( int year );
	List<ReporteDTO> reportePacientesRecomendadosCanalAnualmente( int year );
	List<ReporteDTO> reporteCitasGeneradasAnualmente( int year );
	List<ReporteDTO> reportePacientesHospitalizadosAnualmente( int year );
	List<ReporteDTO> reporteServiciosMasUtilizadosCitasAnualmente( Integer year, Integer areaMedicaId);
	Integer reporteCantidadTotalPacientesAnualmente();
	Integer reporteCantidadTotalCitasAnualmente();
	Integer reporteCantidadTotalPersonalMedicoAnualmente();
	Integer reporteCantidadTotalHospitalizacionAnualmente();
}
