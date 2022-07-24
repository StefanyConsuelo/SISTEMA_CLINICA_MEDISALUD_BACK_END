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

import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.entity.dto.ServicioDTO;
import com.sidet.idat.ws.medisalud.services.ServicioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/servicios")
public class ServicioController {
	
	@Autowired
	private ServicioService<ServicioDTO> servicioService;

	@GetMapping("/{idAreaMedica}/areas-medicas")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ServicioDTO> listarTodos( @PathVariable("idAreaMedica") Integer id ){
		log.info( "ServicioController.listarPorAreaMedicaId( areaMedicaId => {} )", id );
		return servicioService.listarPorAreaMedicaId(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	//@PreAuthorize("hasAuthority('ROLE_CREAR_MEDICAMENTO')")
	public ServicioDTO registrar( @RequestBody ServicioDTO servicioDTO ) {
		log.info( "ServicioController.registrar( ServicioDTO => {})", servicioDTO );
		return servicioService.registrar(servicioDTO);
	}
	
	@PostMapping("/paginado")
	@ResponseStatus(code = HttpStatus.OK)
	//@PreAuthorize("hasAuthority('ROLE_LISTAR_MEDICAMENTO')")
	public PaginadorDTO<ServicioDTO> listarPaginado( @RequestBody PaginadorDTO<ServicioDTO> paginador ){
		log.info("ServicioController.listarPaginado( paginador => {})", paginador );
		return servicioService.listarPaginado(paginador);
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	//@PreAuthorize("hasAuthority('ROLE_EDITAR_MEDICAMENTO')")
	public ServicioDTO actualizar( @RequestBody ServicioDTO servicioDTO ) {
		log.info("ServicioController.actualizar( ServicioDTO => {})", servicioDTO );
		return servicioService.actualizar(servicioDTO);
	}
	
	@DeleteMapping("/{servicioId}")
	@ResponseStatus(code = HttpStatus.OK)
	//@PreAuthorize("hasAuthority('ROLE_ELIMINAR_MEDICAMENTO')")
	public Boolean eliminar( @PathVariable("servicioId") Integer id ) {
		log.info("ServicioController.eliminar( ServicioId => {})", id );
		return servicioService.eliminar(id);
	}
}
