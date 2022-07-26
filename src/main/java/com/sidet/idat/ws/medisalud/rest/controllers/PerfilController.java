package com.sidet.idat.ws.medisalud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sidet.idat.ws.medisalud.entity.dto.PerfilDTO;
import com.sidet.idat.ws.medisalud.services.PerfilService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/perfiles")
public class PerfilController {

	@Autowired
	private PerfilService perfilService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<PerfilDTO> listarTodos(){
		log.info("PerfilController.listarTodos()");
		return perfilService.listarTodos();
	}
	
}
