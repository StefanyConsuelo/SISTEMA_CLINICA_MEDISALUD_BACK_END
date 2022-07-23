package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.AreaMedica;
import com.sidet.idat.ws.medisalud.entity.dto.AreaMedicaDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.exceptions.RecursoNoEncontradoException;
import com.sidet.idat.ws.medisalud.mappers.AreaMedicaMapper;
import com.sidet.idat.ws.medisalud.repository.AreaMedicaRepository;
import com.sidet.idat.ws.medisalud.services.AreaMedicaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AreaMedicaServiceImpl implements AreaMedicaService<AreaMedicaDTO> {

	@Autowired
	private AreaMedicaRepository areaMedicaRepository;
	
	@Autowired
	private AreaMedicaMapper areaMedicaMapper;
	
	@Override
	public List<AreaMedicaDTO> listarTodos() {
		
		log.info("AreaMedicaServiceImpl.listarTodos()");
		
		try {
			  List<AreaMedica> areaMedicas = areaMedicaRepository.listarTodos();
			  return areaMedicaMapper.asAreaMedicaDTOs(areaMedicas);
		} catch (Exception e) {
			throw new DatabaseException( "Error al listar las areas medicas.", e );
		}
		
	}

	@Override
	public AreaMedicaDTO registrar(AreaMedicaDTO areaMedicaDTO) {
		
		log.info("AreaMedicaServiceImpl.registrar( AreaMedicaDTO => {})", areaMedicaDTO );
		AreaMedica areaMedica = areaMedicaMapper.asAreaMedica(areaMedicaDTO);
		
		try {
				areaMedicaRepository.registrarMedicamento( areaMedica );
				areaMedicaDTO.setAreaMedicaId(areaMedica.getAreaMedicaId());
				
		} catch (Exception e) {
			throw new DatabaseException( "Error al registrar el area medica.", e );
		}
		
		return areaMedicaDTO;
	}
	
	@Override
	public PaginadorDTO<AreaMedicaDTO> listarPaginado(PaginadorDTO<AreaMedicaDTO> paginador) {
		
		log.info("AreaMedicaServiceImpl.listarPaginado( paginador => {})", paginador );
		
		Integer inicio = 0;

		if( paginador.getNumeroPagina() == 0 || paginador.getNumeroPagina() == 1 ) {
			inicio = 0;
		} else {
			inicio = (paginador.getNumeroPagina() - 1) * paginador.getTotalFilasPagina();
		}
		
		try {
			
			List<AreaMedica> datos = areaMedicaRepository.listarPaginado(inicio, paginador.getTotalFilasPagina(), paginador.getQuery());
			Integer totalFilas = areaMedicaRepository.contarTotalFilas(paginador.getQuery());
			
			paginador.setTotalFilas(totalFilas);
			paginador.setDatos(areaMedicaMapper.asAreaMedicaDTOs(datos));
			
		} catch (Exception e) {
			throw new DatabaseException( "No se pudo consultar los datos de las áreas médicas.", e );
		}
		
		return paginador;
	}

	@Override
	public AreaMedicaDTO actualizar(AreaMedicaDTO areaMedicaDTO) {
		
		log.info("AreaMedicaServiceImpl.actualizar( AreaMedicaDTO => {})", areaMedicaDTO );
		AreaMedica areaMedica = areaMedicaRepository.buscarPorAreaMedicaId(areaMedicaDTO.getAreaMedicaId());
		
		if( areaMedica == null ) {
			throw new RecursoNoEncontradoException( "No se encontro coincidencia alguna, área médica "
					+"con id " +areaMedicaDTO.getAreaMedicaId()+ " no existe."  );  
		}
		
		areaMedica.setNombre( areaMedicaDTO.getNombre() );
		
		try {
			areaMedicaRepository.actualizarAreaMedica(areaMedica);				
		} catch (Exception e) {
			throw new DatabaseException( "Error no se pudo actualizar el área médica.", e );
		}
		
		return areaMedicaMapper.asAreaMedicaDTO(areaMedica);
	}

	@Override
	public Boolean eliminar(Integer areaMedicaId) {
		
		log.info("AreaMedicaServiceImpl.eliminar( AreaMedicaId => {})", areaMedicaId );
		
		try {
			areaMedicaRepository.eliminarMedicamento(areaMedicaId);
		} catch (Exception e) {
			throw new DatabaseException("Error no se pudo eliminar el área médica.", e );
		}
		
		return true;
		
	}

	@Override
	public AreaMedicaDTO buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
