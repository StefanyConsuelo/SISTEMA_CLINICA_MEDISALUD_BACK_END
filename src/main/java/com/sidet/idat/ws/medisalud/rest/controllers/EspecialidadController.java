package com.sidet.idat.ws.medisalud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.sidet.idat.ws.medisalud.entity.dto.EspecialidadDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.services.impl.EspecialidadServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {
	
	@Autowired
	private EspecialidadServiceImpl especialidadServiceImpl;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<EspecialidadDTO> listarTodos() {
		log.info("EspecialidadController.listarTodos");
		return especialidadServiceImpl.listarTodos();
	}

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public EspecialidadDTO registrar(@RequestBody EspecialidadDTO dto) {
		log.info("EspecialidadController.registrar",dto);
		return especialidadServiceImpl.registrar(dto);
		
	}
	
	@PostMapping("/paginado")
	@ResponseStatus(code = HttpStatus.OK)
	public PaginadorDTO<EspecialidadDTO> listarPaginado(@RequestBody PaginadorDTO<EspecialidadDTO> dto) {
		log.info("EspecialidadController.listarPaginado",dto);
		return especialidadServiceImpl.listarPaginado(dto);
	}
	
	@PutMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public EspecialidadDTO actualizar(@RequestBody EspecialidadDTO dto) {
		log.info("EspecialidadController.actualizar",dto);
		return especialidadServiceImpl.actualizar(dto);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public EspecialidadDTO buscarPorId(@PathVariable Integer id) {
		log.info("EspecialidadController.buscarPorId",id);
		return especialidadServiceImpl.buscarPorId(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Boolean eliminar(@PathVariable Integer id) {
		log.info("EspecialidadController.eliminar",id);
		return especialidadServiceImpl.eliminar(id);
	}
}