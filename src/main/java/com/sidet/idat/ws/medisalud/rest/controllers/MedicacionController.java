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

import com.sidet.idat.ws.medisalud.entity.dto.DetalleMedicacionDTO;
import com.sidet.idat.ws.medisalud.entity.dto.MedicacionDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.services.MedicacionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/medicaciones")
public class MedicacionController {
	
	@Autowired
	private MedicacionService medicacionService;
	
	@GetMapping("/historias-clinicas/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRAR_HOSPITALIZACION')")
	public List<DetalleMedicacionDTO> listarUltimaPorHistoriaClinicaId( @PathVariable("id") Integer historiaClinicaId ){
		log.info( "MedicacionController.listarUltimaPorHistoriaClinicaId( historiaClinicaId => {})", historiaClinicaId );
		return medicacionService.listarUltimaPorHistoriaClinicaId(historiaClinicaId);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_EDITAR_HISTORIA_CLINICA')")
	public MedicacionDTO registrar( @RequestBody MedicacionDTO medicacionDTO ) {
		log.info("MedicacionController.registrar( medicacionDTO = {})", medicacionDTO);
		return medicacionService.registrar(medicacionDTO);
	}
	
	@PostMapping("/paginado")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_EDITAR_HISTORIA_CLINICA')")
	public PaginadorDTO<MedicacionDTO> listarPaginado( @RequestBody PaginadorDTO<MedicacionDTO> paginador ) {
		log.info("MedicacionController.listarPaginado( paginador = {})", paginador);
		return medicacionService.listarPaginado(paginador);
	}
}
