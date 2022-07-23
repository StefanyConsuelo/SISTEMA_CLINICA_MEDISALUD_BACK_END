package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.sidet.idat.ws.medisalud.entity.AreaMedica;
import com.sidet.idat.ws.medisalud.entity.dto.AreaMedicaDTO;

@Mapper(componentModel = "spring")
public interface AreaMedicaMapper {
	
	AreaMedica asAreaMedica( AreaMedicaDTO areaMedicaDTO );
	
	AreaMedicaDTO asAreaMedicaDTO( AreaMedica areaMedica );
	
	List<AreaMedicaDTO> asAreaMedicaDTOs( List<AreaMedica> areasMedicas );
	
}
