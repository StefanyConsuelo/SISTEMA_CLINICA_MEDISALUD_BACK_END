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

import com.sidet.idat.ws.medisalud.entity.dto.MotivoConsultaDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.services.MotivoConsultaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/motivos-consultas")
public class MotivoConsultaController {
	
	@Autowired
	private MotivoConsultaService motivoConsultaService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_EDITAR_HISTORIA_CLINICA')")
	public MotivoConsultaDTO registrar( @RequestBody MotivoConsultaDTO motivoConsultaDTO ) {
		log.info("MotivoConsultaController.registrar( motivoConsultaDTO = {})", motivoConsultaDTO);
		return motivoConsultaService.registrar(motivoConsultaDTO);
	}
	
	@PostMapping("/paginado")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_EDITAR_HISTORIA_CLINICA')")
	public PaginadorDTO<MotivoConsultaDTO> registrar( 
			@RequestBody PaginadorDTO<MotivoConsultaDTO> paginador ) {
		log.info("MotivoConsultaController.listarPaginado( paginador => {})", paginador );
		return motivoConsultaService.listarPaginado(paginador);
	}
}
