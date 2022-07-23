package com.sidet.idat.ws.medisalud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sidet.idat.ws.medisalud.entity.dto.ReporteDTO;
import com.sidet.idat.ws.medisalud.services.ReporteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/reportes")
public class ReporteController {

	@Autowired
	private ReporteService reporteService;
	
	@GetMapping("/pacientes/{year}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ReporteDTO> reportePacientesRegistradosAnualmente( @PathVariable("year") int year ){
		log.info( "ReporteController.reportePacientesRegistradosAnualmente( year => {} )" , year );
		return reporteService.reportePacientesRegistradosAnualmente(year);
	}
	
	@GetMapping("/pacientes/canal/{year}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ReporteDTO> reportePacientesRecomendadosCanalAnualmente( @PathVariable("year") int year ){
		log.info( "ReporteController.reportePacientesRecomendadosCanalAnualmente( year => {} )" , year );
		return reporteService.reportePacientesRecomendadosCanalAnualmente(year);
	}
	
	@GetMapping("/citas-medicas/{year}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ReporteDTO> reporteCitasGeneradasAnualmente( @PathVariable("year") int year ){
		log.info( "ReporteController.reporteCitasGeneradasAnualmente( year => {} )" , year );
		return reporteService.reporteCitasGeneradasAnualmente(year);
	}
	
	@GetMapping("/hospitalizaciones/{year}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ReporteDTO> reportePacientesHospitalizadosAnualmente( @PathVariable("year") int year ){
		log.info( "ReporteController.reportePacientesHospitalizadosAnualmente( year => {} )" , year );
		return reporteService.reportePacientesHospitalizadosAnualmente(year);
	}
	
	@GetMapping("/citas-medicas/anio/{year}/areas-medicas/{areaMedicaId}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ReporteDTO> reporteServiciosMasUtilizadosCitasAnualmente(
			@PathVariable("year") Integer year, @PathVariable("areaMedicaId") Integer areaMedicaId ){
		log.info( "ReporteController.reportePacientesHospitalizadosAnualmente( year => {} )" , year );
		return reporteService.reporteServiciosMasUtilizadosCitasAnualmente(year, areaMedicaId);
	}
	
	@GetMapping("/pacientes/totales/anualmente")
	@ResponseStatus(code = HttpStatus.OK)
	public Integer reporteCantidadTotalPacientesAnualmente(){
		log.info( "ReporteController.reporteCantidadPacientesAnualmente()");
		return reporteService.reporteCantidadTotalPacientesAnualmente();
	}
	@GetMapping("/citas-medicas/totales/anualmente")
	@ResponseStatus(code = HttpStatus.OK)
	public Integer reporteCantidadTotalCitasAnualmente(){
		log.info( "ReporteController.reporteCantidadPacientesAnualmente()");
		return reporteService.reporteCantidadTotalCitasAnualmente();
	}
	
	@GetMapping("/hospitalizaciones/totales/anualmente")
	@ResponseStatus(code = HttpStatus.OK)
	public Integer reporteCantidadTotalHospitalizacionAnualmente(){
		log.info( "ReporteController.reporteCantidadTotalHospitalizacionAnualmente()");
		return reporteService.reporteCantidadTotalHospitalizacionAnualmente();
	}
	@GetMapping("/personal-medico/totales/anualmente")
	@ResponseStatus(code = HttpStatus.OK)
	public Integer reporteCantidadTotalPersonalMedicoAnualmente(){
		log.info( "ReporteController.reporteCantidadTotalPersonalMedicoAnualmente()");
		return reporteService.reporteCantidadTotalPersonalMedicoAnualmente();
	}
}
