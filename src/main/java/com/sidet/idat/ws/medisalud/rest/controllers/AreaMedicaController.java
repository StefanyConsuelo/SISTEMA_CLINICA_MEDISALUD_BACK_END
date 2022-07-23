package com.sidet.idat.ws.medisalud.rest.controllers;

import java.util.List;

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

import com.sidet.idat.ws.medisalud.entity.dto.AreaMedicaDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.services.AreaMedicaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/areas-medicas")
public class AreaMedicaController {

	@Autowired
	private AreaMedicaService<AreaMedicaDTO> areaMedicaService;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<AreaMedicaDTO> listarTodos(){
		log.info( "AreaMedicaController.listarTodos()" );
		return areaMedicaService.listarTodos();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	//@PreAuthorize("hasAuthority('ROLE_CREAR_MEDICAMENTO')")
	public AreaMedicaDTO registrarAreaMedica( @RequestBody AreaMedicaDTO areaMedicaDTO ) {
		log.info( "AreaMedicaController.registrar( AreaMedicaDTO => {})", areaMedicaDTO );
		return areaMedicaService.registrar(areaMedicaDTO);
	}
	
	@PostMapping("/paginado")
	@ResponseStatus(code = HttpStatus.OK)
	//@PreAuthorize("hasAuthority('ROLE_LISTAR_MEDICAMENTO')")
	public PaginadorDTO<AreaMedicaDTO> listarPaginado( @RequestBody PaginadorDTO<AreaMedicaDTO> paginador ){
		log.info("AreaMedicaController.listarPaginado( paginador => {})", paginador );
		return areaMedicaService.listarPaginado(paginador);
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	//@PreAuthorize("hasAuthority('ROLE_EDITAR_MEDICAMENTO')")
	public AreaMedicaDTO actualizar( @RequestBody AreaMedicaDTO areaMedicaDTO ) {
		log.info("AreaMedicaController.actualizar( AreaMedicaDTO => {})", areaMedicaDTO );
		return areaMedicaService.actualizar(areaMedicaDTO);
	}
	
	@DeleteMapping("/{areaMedicaId}")
	@ResponseStatus(code = HttpStatus.OK)
	//@PreAuthorize("hasAuthority('ROLE_ELIMINAR_MEDICAMENTO')")
	public Boolean eliminar( @PathVariable("areaMedicaId") Integer id ) {
		log.info("AreaMedicaController.eliminar( AreaMedicaId => {})", id );
		return areaMedicaService.eliminar(id);
	}
	
}
