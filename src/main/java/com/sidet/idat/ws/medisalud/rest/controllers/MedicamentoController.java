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

import com.sidet.idat.ws.medisalud.entity.dto.MedicamentoDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.services.MedicamentoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

	@Autowired
	private MedicamentoService<MedicamentoDTO> medicamentoService;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<MedicamentoDTO> listarTodos() {
		log.info("MedicamentoController.listarTodos()");
		return medicamentoService.listarTodos();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	//@PreAuthorize("hasAuthority('ROLE_CREAR_MEDICAMENTO')")
	public MedicamentoDTO registrarMedicamento( @RequestBody MedicamentoDTO medicamentoDTO ) {
		log.info( "MedicamentoController.registrar( medicamentoDTO => {})", medicamentoDTO );
		return medicamentoService.registrar(medicamentoDTO);
	}
	
	@PostMapping("/paginado")
	@ResponseStatus(code = HttpStatus.OK)
	//@PreAuthorize("hasAuthority('ROLE_LISTAR_MEDICAMENTO')")
	public PaginadorDTO<MedicamentoDTO> listarPaginado( @RequestBody PaginadorDTO<MedicamentoDTO> paginador ){
		log.info("MedicamentoController.listarPaginado( paginador => {})", paginador );
		return medicamentoService.listarPaginado(paginador);
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	//@PreAuthorize("hasAuthority('ROLE_EDITAR_MEDICAMENTO')")
	public MedicamentoDTO actualizar( @RequestBody MedicamentoDTO medicamentoDTO ) {
		log.info("MedicamentoController.actualizar( medicamentoDTO => {})", medicamentoDTO );
		return medicamentoService.actualizar(medicamentoDTO);
	}
	
	@DeleteMapping("/{medicamentoId}")
	@ResponseStatus(code = HttpStatus.OK)
	//@PreAuthorize("hasAuthority('ROLE_ELIMINAR_MEDICAMENTO')")
	public Boolean eliminar( @PathVariable("medicamentoId") Integer id ) {
		log.info("MedicamentoController.eliminar( MedicamentoId => {})", id );
		return medicamentoService.eliminar(id);
	}
}
