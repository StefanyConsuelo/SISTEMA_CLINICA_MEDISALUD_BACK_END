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
import com.sidet.idat.ws.medisalud.entity.dto.PersonalMedicoDTO;
import com.sidet.idat.ws.medisalud.services.impl.PersonalMedicoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/personal-medico")
public class PersonalMedicoController {
	
	@Autowired
	private PersonalMedicoServiceImpl personalMedicoServiceImpl;
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_CREAR_PERSONAL_MEDICO')")
	public PersonalMedicoDTO registroPersonalMedico(@RequestBody PersonalMedicoDTO persona) {
		log.info("PersonalMedicoController.registroPersonalMedico( dto => {})",persona.toString());
		return personalMedicoServiceImpl.registrar(persona);
	}
	
	@PostMapping("/paginado")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_LISTAR_PERSONAL_MEDICO')")
	public PaginadorDTO<PersonalMedicoDTO> listarPaginado( @RequestBody PaginadorDTO<PersonalMedicoDTO> dto) {
		log.info("PersonalMedicoController.listarPaginado( dto => {})",dto.toString());
		return personalMedicoServiceImpl.listarPaginado(dto);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_LISTAR_PERSONAL_MEDICO')")
	public PersonalMedicoDTO buscarPorId(@PathVariable Integer id) {
		log.info("PersonalMedicoController.buscarPorId( id => {})",id);
		return personalMedicoServiceImpl.buscarPorId(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_ELIMINAR_PERSONAL_MEDICO')")
	public Boolean eliminar(@PathVariable Integer id) {
		log.info("PersonalMedicoController.eliminar( id => {})",id);
		return personalMedicoServiceImpl.eliminar(id);
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_EDITAR_PERSONAL_MEDICO')")
	public PersonalMedicoDTO actualizar(@RequestBody PersonalMedicoDTO medicoDTO) {
		log.info("PersonalMedicoController.actualizar( id => {})",medicoDTO);
		personalMedicoServiceImpl.actualizar(medicoDTO);
		return personalMedicoServiceImpl.buscarPorId(medicoDTO.getPersonalMedicoId());
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<PersonalMedicoDTO> listarTodos() {
		log.info("PersonalMedicoController.listarTodos()");
		return personalMedicoServiceImpl.listarTodos();
	}
}
