package com.sidet.idat.ws.medisalud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sidet.idat.ws.medisalud.entity.dto.HospitalizacionDTO;
import com.sidet.idat.ws.medisalud.services.HospitalizacionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/hospitalizaciones")
public class HospitalizacionController {
	
	@Autowired
	private HospitalizacionService<HospitalizacionDTO> hospitalizacionService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRAR_HOSPITALIZACION')")
	public HospitalizacionDTO registrar( @RequestBody HospitalizacionDTO hospitalizacionDTO ) {
		log.info( "HospitalizacionController.registrar( hospitalizacionDTO => {})", hospitalizacionDTO );
		return hospitalizacionService.registrar(hospitalizacionDTO);
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRAR_HOSPITALIZACION')")
	public List<HospitalizacionDTO> listarTodos(){
		log.info( "HospitalizacionController.listarTodos()" );
		return hospitalizacionService.listarTodos();
	}
	
	@GetMapping("/{hospitalizacionId}/alta")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRAR_HOSPITALIZACION')")
	public Boolean darAlt( @PathVariable Integer hospitalizacionId ){
		log.info( "HospitalizacionController.listarTodos()" );
		hospitalizacionService.darAlta(hospitalizacionId);
		return true;
	}
	
	@GetMapping("/{hospitalizacionId}/camas/{camaId}/disponer")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRAR_HOSPITALIZACION')")
	public Boolean darDisponerCama( @PathVariable Integer hospitalizacionId, @PathVariable Integer camaId){
		log.info( "HospitalizacionController.listarTodos()" );
		hospitalizacionService.darDisponibilidadCama(hospitalizacionId, camaId);
		return true;
	}
	
	
}
