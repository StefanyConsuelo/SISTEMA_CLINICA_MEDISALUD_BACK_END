package com.sidet.idat.ws.medisalud.services;

import java.util.List;

import com.sidet.idat.ws.medisalud.entity.dto.DetalleMedicacionDTO;
import com.sidet.idat.ws.medisalud.entity.dto.MedicacionDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;

public interface MedicacionService {

	List<DetalleMedicacionDTO> listarUltimaPorHistoriaClinicaId( Integer historiaClinicaId );
	
	MedicacionDTO registrar( MedicacionDTO medicacionDTO );
	
	PaginadorDTO<MedicacionDTO> listarPaginado( PaginadorDTO<MedicacionDTO> paginador );
}
