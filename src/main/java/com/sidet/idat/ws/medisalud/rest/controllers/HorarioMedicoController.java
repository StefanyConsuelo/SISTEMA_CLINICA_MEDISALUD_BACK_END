package com.sidet.idat.ws.medisalud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sidet.idat.ws.medisalud.entity.dto.HorarioMedicoDTO;
import com.sidet.idat.ws.medisalud.services.HorarioMedicoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RequestMapping("/horario-medicos")
@RestController
public class HorarioMedicoController {

	@Autowired
	private HorarioMedicoService<HorarioMedicoDTO> horarioMedicoService;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<HorarioMedicoDTO> listarTodos() {
		log.info("HorarioMedicoController.listarTodos");
		return horarioMedicoService.listarTodos();
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public HorarioMedicoDTO buscarPodId(@PathVariable Integer id) {
		log.info("HorarioMedicoController.buscarPodId( id => {})",id);
		return horarioMedicoService.buscarPorId(id);
	}
	
	@PutMapping()
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_CONFIGURAR_HORARIO_PERSONAL_MEDICO')")
	public HorarioMedicoDTO actualizar(@RequestBody HorarioMedicoDTO horarioMedico) {
		log.info("HorarioMedicoController.actualizar( horarioMedico => {})",horarioMedico);
		horarioMedicoService.actualizar(horarioMedico);
		return horarioMedicoService.buscarPorId(horarioMedico.getPersonalMedicoId());
	}

	@GetMapping("/{servicioId}/servicios")
	@ResponseStatus(code = HttpStatus.OK)
	public List<HorarioMedicoDTO> listarPorServicioId( @PathVariable("servicioId") Integer id ) {
		log.info( "HorarioMedicoController.listarPorServicioId( servicioId => {} )", id );
		return horarioMedicoService.listarPorServicioId(id);
	}
	
}
