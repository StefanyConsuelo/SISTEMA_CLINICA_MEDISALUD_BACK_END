package com.sidet.idat.ws.medisalud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sidet.idat.ws.medisalud.entity.dto.DosisMedicacionDTO;
import com.sidet.idat.ws.medisalud.services.DosisMedicacionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/dosis-medicaciones")
public class DosisMedicacionController {
	
	@Autowired
	private DosisMedicacionService dosisMedicacionService;
	
	@GetMapping("/historias-clinicas/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRAR_HOSPITALIZACION')")
	public List<DosisMedicacionDTO> listarSiguienteMedicacionPorHistoriaClinicaId( @PathVariable("id") Integer historiaClinicaId ){
		log.info( "DosisMedicacionController.listarSiguienteMedicacionPorHistoriaClinicaId( historiaClinicaId => {})", historiaClinicaId );
		return dosisMedicacionService.listarSiguienteMedicacionPorHistoriaClinicaId(historiaClinicaId);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_MARCAR_DOSIS_HOSPITALIZACION')")
	public void actualizarEstado( @PathVariable("id") Integer dosisMedicacionId ) {
		log.info( "DosisMedicacionController.actualizarEstado( dosisMedicacionId => {})", dosisMedicacionId );
		dosisMedicacionService.actualizarEstado( dosisMedicacionId );
	}
	
	@PutMapping("/alerta/dosis")
	@ResponseStatus(code = HttpStatus.OK)
	//@PreAuthorize("hasAuthority('ROLE_MARCAR_DOSIS_HOSPITALIZACION')")
	public List<Integer> alertarDosisPacientes( @RequestBody List<Integer> pacientesIds) {
		log.info("DosisMedicacionController.alertarDosisPacientes( pacientesIds = {} )", pacientesIds);
		return dosisMedicacionService.alertarDosisPacientes(pacientesIds);
	}
}
