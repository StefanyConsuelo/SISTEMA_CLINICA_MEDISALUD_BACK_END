package com.sidet.idat.ws.medisalud.services;

import java.util.List;

import com.sidet.idat.ws.medisalud.entity.dto.DosisMedicacionDTO;

public interface DosisMedicacionService {

	List<DosisMedicacionDTO> listarSiguienteMedicacionPorHistoriaClinicaId( Integer historiaClinicaId );
	
	void actualizarEstado( Integer dosisMedicacionId );
	
	List<Integer> alertarDosisPacientes( List<Integer> pacientesIds );
	
}
