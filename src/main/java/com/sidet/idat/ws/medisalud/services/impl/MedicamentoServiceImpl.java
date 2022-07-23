package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.Medicamento;
import com.sidet.idat.ws.medisalud.entity.dto.MedicamentoDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.exceptions.RecursoNoEncontradoException;
import com.sidet.idat.ws.medisalud.mappers.MedicamentoMapper;
import com.sidet.idat.ws.medisalud.repository.MedicamentoRepository;
import com.sidet.idat.ws.medisalud.services.MedicamentoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicamentoServiceImpl implements MedicamentoService<MedicamentoDTO> {

	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private MedicamentoMapper medicamentoMapper;

	@Override
	public List<MedicamentoDTO> listarTodos() {
		log.info("MedicamentoServiceImpl.listarTodos()");
		return medicamentoMapper.asMedicamentoDTOs(medicamentoRepository.listarTodos());
	}

	@Override
	public MedicamentoDTO registrar( MedicamentoDTO medicamentoDTO ) {
		
		log.info("MedicamentoServiceImpl.registrar( MedicamentoDTO => {})", medicamentoDTO );
		Medicamento medicamento = medicamentoMapper.asMedicamento(medicamentoDTO);
		
		try {
				medicamentoRepository.registrarMedicamento( medicamento );
				medicamentoDTO.setMedicamentoId(medicamento.getMedicamentoId());
				
		} catch (Exception e) {
			throw new DatabaseException( "Error al registrar el medicamento.", e );
		}
		
		return medicamentoDTO;
	}

	@Override
	public PaginadorDTO<MedicamentoDTO> listarPaginado( PaginadorDTO<MedicamentoDTO> paginador ) {
		
		log.info("MedicamentoServiceImpl.listarPaginado( paginador => {})", paginador );
		
		Integer inicio = 0;

		if( paginador.getNumeroPagina() == 0 || paginador.getNumeroPagina() == 1 ) {
			inicio = 0;
		} else {
			inicio = (paginador.getNumeroPagina() - 1) * paginador.getTotalFilasPagina();
		}
		
		try {
			
			List<Medicamento> datos = medicamentoRepository.listarPaginado(
					inicio, paginador.getTotalFilasPagina(), paginador.getQuery() );
			Integer totalFilas = medicamentoRepository.contarTotalFilas(paginador.getQuery());
			
			paginador.setTotalFilas(totalFilas);
			paginador.setDatos(medicamentoMapper.asMedicamentoDTOs(datos));
			
		} catch (Exception e) {
			throw new DatabaseException( "No se pudo consultar los datos de los medicamentos.", e );
		}
		
		return paginador;
	}
	
	@Override
	public MedicamentoDTO actualizar( MedicamentoDTO medicamentoDTO ) {
		
		log.info("MedicamentoServiceImpl.actualizar( MedicamentoDTO => {})", medicamentoDTO );
		Medicamento medicamento = medicamentoRepository.buscarPorMedicamentoId(medicamentoDTO.getMedicamentoId());
		
		if( medicamento == null ) {
			throw new RecursoNoEncontradoException( "No se encontro coincidencia alguna, medicamento "
					+"con id " +medicamentoDTO.getMedicamentoId()+ " no existe."  );  
		}
		
		medicamento.setNombre( medicamentoDTO.getNombre() );
		
		try {
			medicamentoRepository.actualizarMedicamento(medicamento);				
		} catch (Exception e) {
			throw new DatabaseException( "Error no se pudo actualizar el medicamento.", e );
		}
		
		return medicamentoMapper.asMedicamentoDTO(medicamento);
	}

	@Override
	public Boolean eliminar( Integer medicamentoId ) {
		
		log.info("MedicamentoServiceImpl.eliminar( MedicamentoId => {})", medicamentoId );
		
		try {
			medicamentoRepository.eliminarMedicamento(medicamentoId);
		} catch (Exception e) {
			throw new DatabaseException("Error no se pudo eliminar el medicamento.", e );
		}
		
		return true;
	}
	
	@Override
	public MedicamentoDTO buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
