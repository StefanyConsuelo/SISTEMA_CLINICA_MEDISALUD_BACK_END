package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.Examen;
import com.sidet.idat.ws.medisalud.entity.dto.ExamenDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.mappers.ExamenMapper;
import com.sidet.idat.ws.medisalud.repository.ExamenRespository;
import com.sidet.idat.ws.medisalud.services.ExamenService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExamenServiceImpl implements ExamenService<ExamenDTO>{

	@Autowired
	private ExamenRespository examenRespository;
	
	@Autowired
	private ExamenMapper examenMapper;
	
	@Override
	public List<ExamenDTO> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExamenDTO buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExamenDTO registrar(ExamenDTO t) {
		log.info("EspecialidadServiceImpl.listarTodos",t);
		Examen examen = examenMapper.asExamen(t);
		try {
			examenRespository.registrarExamen(examen);
		} catch (Exception e) {
			throw new DatabaseException("Error al registrar el examen ",e);
		}
		t.setExamenId(examen.getExamenId());
		return t;
	}

	@Override
	public ExamenDTO actualizar(ExamenDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminar(Integer id) {
		return null;
	}

	@Override
	public PaginadorDTO<ExamenDTO> listarPaginado(PaginadorDTO<ExamenDTO> paginador) {
		log.info("EspecialidadServiceImpl.listarPaginado( paginador => {})", paginador );
		
		Integer inicio = 0;

		if( paginador.getNumeroPagina() == 0 || paginador.getNumeroPagina() == 1 ) {
			inicio = 0;
		} else {
			inicio = (paginador.getNumeroPagina() - 1) * paginador.getTotalFilasPagina();
		}
		
		try {
			
			List<Examen> datos = examenRespository.listarPaginado( 
					inicio, paginador.getTotalFilasPagina(), paginador.getObjeto().getHistoriaClinicaId() );
			
			Integer totalFilas = examenRespository.contarTotalFilas(paginador.getObjeto().getHistoriaClinicaId());
			
			paginador.setTotalFilas(totalFilas);
			paginador.setDatos(examenMapper.asExamenDTOs(datos));
			
		} catch (Exception e) {
			throw new DatabaseException( "No se pudo consultar los datos de examenes.", e );
		}
		
		return paginador;
	}

	@Override
	public ExamenDTO editarResultado(ExamenDTO examenDTO) {
		log.info("EspecialidadServiceImpl.editarResultado( examenDTO = {})", examenDTO);
		Examen examen = examenMapper.asExamen(examenDTO);
		try {
			examenRespository.editarResultado(examen);
		} catch (Exception e) {
			throw new DatabaseException("Error al editar el resultado del examen ",e);
		}
		return examenDTO;
	}

	@Override
	public Boolean finalizarExamen(Integer examenId) {
		log.info("EspecialidadServiceImpl.eliminar( examenId = {})", examenId);
		try {
			examenRespository.finalizarExamen(examenId);
			return true;
		} catch (Exception e) {
			throw new DatabaseException("Error al registrar el examen ",e);
		}
	}

}
