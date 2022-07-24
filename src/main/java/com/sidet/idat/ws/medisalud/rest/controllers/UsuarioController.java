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

import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.entity.dto.UsuarioDTO;
import com.sidet.idat.ws.medisalud.services.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService<UsuarioDTO> usuarioService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_CREAR_USUARIO')")
	public UsuarioDTO registrar( @RequestBody UsuarioDTO usuarioDTO ) {
		log.info("UsuarioController.registrar( usuarioDTO = {})", usuarioDTO);
		return usuarioService.registrar(usuarioDTO);
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_EDITAR_USUARIO')")
	public UsuarioDTO actualizar( @RequestBody UsuarioDTO usuarioDTO ) {
		log.info("UsuarioController.actualizar( usuarioDTO = {})", usuarioDTO);
		return usuarioService.actualizar(usuarioDTO);
	}

	@DeleteMapping("/{usuarioId}")
	@PreAuthorize("hasAuthority('ROLE_ELIMINAR_USUARIO')")
	@ResponseStatus(code = HttpStatus.OK)
	public Boolean eliminar( @PathVariable Integer usuarioId ) {
		log.info("UsuarioController.actualizar( usuarioId = {})", usuarioId);
		return usuarioService.eliminar(usuarioId);
	}
	
	@PostMapping("/paginado")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_LISTAR_USUARIO')")
	public PaginadorDTO<UsuarioDTO> listarPaginado( @RequestBody PaginadorDTO<UsuarioDTO> dto) {
		log.info("UsuarioController.listarPaginado( dto => {})", dto);
		return usuarioService.listarPaginado(dto);
	}
	
	@GetMapping("/{usuarioId}")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_LISTAR_USUARIO')")
	public UsuarioDTO buscarPorId(@PathVariable Integer usuarioId) {
		log.info("UsuarioController.buscarPorId( id => {})", usuarioId);
		return usuarioService.buscarPorId(usuarioId);
	}
	
	@GetMapping("/{usuarioId}/bloquear")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_BLOQUEAR_USUARIO')")
	public Boolean bloquear(@PathVariable Integer usuarioId) {
		log.info("UsuarioController.bloquear( id => {})", usuarioId);
		return usuarioService.bloquear(usuarioId);
	}
	
	@GetMapping("/{usuarioId}/desbloquear")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_BLOQUEAR_USUARIO')")
	public Boolean desbloquear(@PathVariable Integer usuarioId) {
		log.info("UsuarioController.desbloquear( id => {})", usuarioId);
		return usuarioService.desbloquear(usuarioId);
	}
}
