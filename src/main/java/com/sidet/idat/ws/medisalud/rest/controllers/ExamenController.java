package com.sidet.idat.ws.medisalud.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ExamenDTO editarResultado(@RequestBody ExamenDTO examenDTO) {
		log.info("ExamenController.editarResultado( dto = {})",examenDTO);
		return examenService.editarResultado(examenDTO);
	}
	
	@DeleteMapping("/{examenId}")
	@ResponseStatus(code = HttpStatus.OK)
	public Boolean finalizarExamen(@PathVariable Integer examenId) {
		log.info("ExamenController.finalizarExamen( examenId = {})",examenId);
		return examenService.finalizarExamen(examenId);
	}
}
