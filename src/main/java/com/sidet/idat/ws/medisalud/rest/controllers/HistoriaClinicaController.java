package com.sidet.idat.ws.medisalud.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sidet.idat.ws.medisalud.entity.dto.HistoriaClinicaDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.services.HistoriaClinicaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/historias-clinicas")
public class HistoriaClinicaController {
	
	@Autowired
	private HistoriaClinicaService<HistoriaClinicaDTO> historiaClinicaService;
	
	@PostMapping("/paginado")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_LISTAR_HISTORIA_CLINICA')")
	public PaginadorDTO<HistoriaClinicaDTO> listarPaginado( 
			@RequestBody PaginadorDTO<HistoriaClinicaDTO> paginador ){
		log.info("HistoriaClinicaController.listarPaginado( paginador = {})", paginador);
		return historiaClinicaService.listarPaginado(paginador);
	}
}
