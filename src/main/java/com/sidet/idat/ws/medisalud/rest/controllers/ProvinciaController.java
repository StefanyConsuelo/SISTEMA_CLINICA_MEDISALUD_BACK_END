package com.sidet.idat.ws.medisalud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sidet.idat.ws.medisalud.entity.dto.ProvinciaDTO;
import com.sidet.idat.ws.medisalud.services.ProvinciaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/provincias")
public class ProvinciaController {
	
	@Autowired
	private ProvinciaService provinciaService;
	
	@GetMapping("/{idDepartamento}/departamentos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProvinciaDTO> listarTodos( @PathVariable("idDepartamento") Integer id ){
		log.info( "ProvinciaController.listarPorDepartamentoId( departamentoId => {} )", id );
		return provinciaService.listarPorDepartamentoId( id );
	}

}
