package com.sidet.idat.ws.medisalud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sidet.idat.ws.medisalud.entity.dto.CamaDTO;
import com.sidet.idat.ws.medisalud.services.CamaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/camas")
public class CamaController {

	@Autowired
	private CamaService camaService;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<CamaDTO> listarTodos(){
		log.info( "CamaController.listarTodos()" );
		return camaService.listarTodos();
	}
	
}
