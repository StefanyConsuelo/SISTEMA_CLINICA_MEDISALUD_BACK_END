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

import com.sidet.idat.ws.medisalud.entity.dto.PacienteDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.entity.dto.TriajePacienteDTO;
import com.sidet.idat.ws.medisalud.services.PacienteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	
	@Autowired
	private PacienteService<PacienteDTO> pacienteService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_CREAR_PACIENTE')")
	public PacienteDTO registrar( @RequestBody PacienteDTO pacienteDTO ) {
		log.info( "PacienteController.registrar( pacienteDTO => {})", pacienteDTO );
		return pacienteService.registrar(pacienteDTO);
	}
	
	@PostMapping("/paginado")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_LISTAR_PACIENTE')")
	public PaginadorDTO<PacienteDTO> listarPaginado( @RequestBody PaginadorDTO<PacienteDTO> paginador ){
		log.info("PacienteController.listarPaginado( paginador => {})", paginador );
		return pacienteService.listarPaginado(paginador);
	}
	
	@GetMapping("/{idPaciente}")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_LISTAR_PACIENTE')")
	public PacienteDTO buscarPorId( @PathVariable("idPaciente") Integer id ){
		log.info("PacienteController.buscarPorId( idPaciente => {})", id );
		return  pacienteService.buscarPorId(id);
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_EDITAR_PACIENTE')")
	public PacienteDTO actualizar( @RequestBody PacienteDTO pacienteDTO ) {
		log.info("PacienteController.actualizar( pacienteDTO => {})", pacienteDTO );
		return pacienteService.actualizar(pacienteDTO);
	}
	
	@DeleteMapping("/{idPaciente}")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_ELIMINAR_PACIENTE')")
	public Boolean eliminar( @PathVariable("idPaciente") Integer id ) {
		log.info("PacienteController.eliminar( pacienteId => {})", id );
		return pacienteService.eliminar(id);
	}
	
	@GetMapping("/documentos/{numeroDocumento}/{tipoDocumento}")
	@ResponseStatus(code = HttpStatus.OK)
	public PacienteDTO buscarPorNumeroDocumento( @PathVariable String numeroDocumento, @PathVariable String tipoDocumento  ){
		log.info("PacienteController.buscarPorId( numeroDocumento => {}, tipoDocumento => {})", numeroDocumento, tipoDocumento );
		return pacienteService.buscarPorNumeroDocumento(numeroDocumento, tipoDocumento);
	}
	
	@PutMapping("/triaje")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_EDITAR_PACIENTE')")
	public PacienteDTO actualizarTriaje( @RequestBody PacienteDTO pacienteDTO ) {
		log.info("PacienteController.actualizarTriaje( pacienteDTO => {})", pacienteDTO );
		return pacienteService.actualizarTriaje(pacienteDTO);
	}
	
	@GetMapping("/triaje/hc/{idHistoriaClinica}")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_EDITAR_PACIENTE')")
	public TriajePacienteDTO listarTriajeUltimo( @PathVariable Integer idHistoriaClinica ) {
		log.info("PacienteController.listarTriajeUltimo( idHistoriaClinica => {})", idHistoriaClinica );
		return pacienteService.listarTriajeUltimo(idHistoriaClinica);
	}
}
