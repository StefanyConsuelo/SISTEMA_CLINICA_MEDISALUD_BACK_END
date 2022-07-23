package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.dto.ServicioDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.exceptions.RecursoNoEncontradoException;
import com.sidet.idat.ws.medisalud.entity.AreaMedica;
import com.sidet.idat.ws.medisalud.entity.Servicio;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.mappers.AreaMedicaMapper;
import com.sidet.idat.ws.medisalud.mappers.ServicioMapper;
import com.sidet.idat.ws.medisalud.repository.AreaMedicaRepository;
import com.sidet.idat.ws.medisalud.repository.ServicioRepository;
import com.sidet.idat.ws.medisalud.services.ServicioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServicioServiceImpl implements ServicioService<ServicioDTO>{

	@Autowired
	private ServicioRepository servicioRepository;
	
	@Autowired
	private AreaMedicaRepository areaMedicaRepository;
	
	@Autowired
	private ServicioMapper servicioMapper;
	
	@Autowired
	private AreaMedicaMapper areaMedicaMapper;
	
	@Override
	public List<ServicioDTO> listarPorAreaMedicaId( Integer areaMedicaId ) {
		
		log.info("ServicioServiceImpl.listarPorAreaMedicaId( areaMedicaId => {} )" , areaMedicaId );
		
		try {
			List<Servicio> servicios = servicioRepository.listarPorAreaMedicaId(areaMedicaId);
			  return servicioMapper.asServiciosDTOs(servicios);
		} catch (Exception e) {
			throw new DatabaseException( "Error al listar los servicios.", e );
		}
		
	}

	@Override
	public ServicioDTO registrar(ServicioDTO servicioDTO) {
		
		log.info("ServicioServiceImpl.registrar( ServicioDTO => {})", servicioDTO );
		AreaMedica areaMedica = areaMedicaRepository.buscarPorAreaMedicaId(servicioDTO.getAreaMedica().getAreaMedicaId());
		
		if( areaMedica == null ) {
			throw new RecursoNoEncontradoException( "No se encontro coincidencia alguna, área médica "
					+"con id " +servicioDTO.getAreaMedica().getAreaMedicaId()+ " no existe."  );  
		}
		
		Servicio servicio = servicioMapper.asServicio(servicioDTO);
		
		try {
			servicioRepository.registrarServicio( servicio );
			servicioDTO.setServicioId(servicio.getServicioId());
				
		} catch (Exception e) {
			throw new DatabaseException( "Error al registrar el servicio.", e );
		}
		
		return servicioDTO;
	}
	
	@Override
	public PaginadorDTO<ServicioDTO> listarPaginado(PaginadorDTO<ServicioDTO> paginador) {
		
		log.info("ServicioServiceImpl.listarPaginado( paginador => {})", paginador );
		
		Integer inicio = 0;

		if( paginador.getNumeroPagina() == 0 || paginador.getNumeroPagina() == 1 ) {
			inicio = 0;
		} else {
			inicio = (paginador.getNumeroPagina() - 1) * paginador.getTotalFilasPagina();
		}
		
		try {
			
			List<Servicio> datos = servicioRepository.listarPaginado( inicio, paginador.getTotalFilasPagina(), paginador.getQuery() );
			Integer totalFilas = servicioRepository.contarTotalFilas(paginador.getQuery());
			
			paginador.setTotalFilas(totalFilas);
			paginador.setDatos(servicioMapper.asServiciosDTOs(datos));
			
		} catch (Exception e) {
			throw new DatabaseException( "No se pudo consultar los datos de los servicios.", e );
		}
		
		return paginador;
	}
	
	@Override
	public ServicioDTO actualizar(ServicioDTO servicioDTO) {
		
		log.info("ServicioServiceImpl.actualizar( ServicioDTO => {})", servicioDTO );
		AreaMedica areaMedica = areaMedicaRepository.buscarPorAreaMedicaId(servicioDTO.getAreaMedica().getAreaMedicaId());
		
		if( areaMedica == null ) {
			throw new RecursoNoEncontradoException( "No se encontro coincidencia alguna, área médica "
					+"con id " +servicioDTO.getAreaMedica().getAreaMedicaId()+ " no existe."  );  
		}
		
		
		Servicio servicio = servicioRepository.buscarPorServicioId(servicioDTO.getServicioId());
		
		if( servicio == null ) {
			throw new RecursoNoEncontradoException( "No se encontro coincidencia alguna, servicio "
					+"con id " +servicioDTO.getServicioId()+ " no existe."  );  
		}
		
		servicio.setNombre( servicioDTO.getNombre() );
		servicio.setAreaMedica( areaMedicaMapper.asAreaMedica(servicioDTO.getAreaMedica()) );
		
		try {
			servicioRepository.actualizarServicio(servicio);				
		} catch (Exception e) {
			throw new DatabaseException( "Error no se pudo actualizar el servicio.", e );
		}
		
		return servicioMapper.asServiciosDTO(servicio);
	}

	@Override
	public Boolean eliminar(Integer servicioId) {
		
		log.info("ServicioServiceImpl.eliminar( ServicioId => {})", servicioId );
		
		try {
			servicioRepository.eliminarServicio(servicioId);
		} catch (Exception e) {
			throw new DatabaseException("Error no se pudo eliminar el servicio.", e );
		}
		
		return true;
	}
	
	@Override
	public List<ServicioDTO> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServicioDTO buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
