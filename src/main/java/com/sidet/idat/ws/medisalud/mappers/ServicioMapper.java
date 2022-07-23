package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.sidet.idat.ws.medisalud.entity.AreaMedica;
import com.sidet.idat.ws.medisalud.entity.Servicio;
import com.sidet.idat.ws.medisalud.entity.dto.AreaMedicaDTO;
import com.sidet.idat.ws.medisalud.entity.dto.ServicioDTO;

@Mapper(componentModel = "spring")
public interface ServicioMapper {
	
	Servicio asServicio( ServicioDTO servicioDTO );
	
	ServicioDTO asServiciosDTO( Servicio servicios );

	List<ServicioDTO> asServiciosDTOs( List<Servicio> servicios );

	AreaMedicaDTO asAreaMedicaDTO( AreaMedica areaMedica );
}