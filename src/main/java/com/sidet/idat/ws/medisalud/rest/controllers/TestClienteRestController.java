package com.sidet.idat.ws.medisalud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.entity.dto.TestClienteDTO;
import com.sidet.idat.ws.medisalud.services.TestClienteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/test-clientes")
public class TestClienteRestController {

	@Autowired
	private TestClienteService<TestClienteDTO> testClienteService;
	
	@GetMapping
	public List<TestClienteDTO> listarTodos(){
		log.info("TestClienteRestController.listarTodos");
		return  testClienteService.listarTodos();
	}
	
	@GetMapping("/paginado")
	public PaginadorDTO<TestClienteDTO> listarPaginado(
		@RequestBody PaginadorDTO<TestClienteDTO> paginador ){
		log.info("TestClienteRestController.listarPaginado( paginador => {})", paginador);
		return testClienteService.listarPaginado(paginador);
	}
	
	@PostMapping
	public TestClienteDTO registrar( @RequestBody TestClienteDTO testClienteDTO ) {
		log.info("TestClienteRestController.registrar( testClienteDTO => {})", testClienteDTO);
		return testClienteService.registrar(testClienteDTO);
	}
	
	@PutMapping
	public TestClienteDTO actualizar( @RequestBody TestClienteDTO testClienteDTO ) {
		log.info("TestClienteRestController.actualizar( testClienteDTO => {})", testClienteDTO);
		return testClienteService.actualizar(testClienteDTO);
	}
	
	@DeleteMapping("/{id}")
	public Boolean eliminar( @PathVariable Integer id ) {
		log.info("TestClienteRestController.eliminar( id => {})", id );
		return testClienteService.eliminar(id);
	}
	
	@GetMapping("/{id}")
	public TestClienteDTO buscarPorId( Integer id ){
		log.info("TestClienteRestController.buscarPorId( id => {})", id);
		return  testClienteService.buscarPorId(id);
	}
}
