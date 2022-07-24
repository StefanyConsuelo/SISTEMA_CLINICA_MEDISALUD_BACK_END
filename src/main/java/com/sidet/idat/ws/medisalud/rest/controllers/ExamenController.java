package com.sidet.idat.ws.medisalud.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sidet.idat.ws.medisalud.entity.dto.ExamenDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.services.ExamenService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/examenes")
public class ExamenController {
	
	@Autowired	
	private ExamenService<ExamenDTO> examenService;
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_HC_SOLICITAR_EXAMEN')")
	public ExamenDTO registrar(@RequestBody ExamenDTO examenDTO) {
		log.info("ExamenController.registrar( examenDTO = {})", examenDTO);
		return examenService.registrar(examenDTO);		
	}

	@PostMapping("/paginado")
	@ResponseStatus(code = HttpStatus.OK)
	public PaginadorDTO<ExamenDTO> listarPaginado(@RequestBody PaginadorDTO<ExamenDTO> dto) {
		log.info("ExamenController.listarPaginado",dto);
		return examenService.listarPaginado(dto);
	}
	
	@PutMapping("/resultado")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_EDITAR_RESULTADO')")
	public ExamenDTO editarResultado(@RequestBody ExamenDTO examenDTO) {
		log.info("ExamenController.editarResultado( dto = {})",examenDTO);
		return examenService.editarResultado(examenDTO);
	}
	
	@DeleteMapping("/{examenId}")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_FINALIZAR_EXAMEN')")
	public Boolean finalizarExamen(@PathVariable Integer examenId) {
		log.info("ExamenController.finalizarExamen( examenId = {})",examenId);
		return examenService.finalizarExamen(examenId);
	}
	
	@GetMapping("/actualizarEstadoCitaGenerada/{examenId}")
	@PreAuthorize("hasAuthority('ROLE_GENERAR_CITA_EXAMEN')")
	@ResponseStatus(code = HttpStatus.OK)
	public Boolean actualizarEstadoCitaGenerada(@PathVariable Integer examenId) {
		log.info("ExamenController.actualizarEstadoCitaGenerada( examenId = {})",examenId);
		return examenService.actualizarEstadoCitaGenerada(examenId);
	}
}
