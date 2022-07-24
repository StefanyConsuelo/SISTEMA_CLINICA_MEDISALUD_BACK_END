package com.sidet.idat.ws.medisalud.mappers;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.sidet.idat.ws.medisalud.entity.Examen;
import com.sidet.idat.ws.medisalud.entity.dto.ExamenDTO;

@Mapper(componentModel = "spring")
public interface ExamenMapper {
	
	Examen asExamen( ExamenDTO examenDTO );
	
	@Mapping(target = "codigo", source = "examenId", qualifiedByName = "formatCodigoExamen")
	ExamenDTO asExamenDTO( Examen examen );
	
	List<Examen> asExamenes( List<ExamenDTO> examenDTOs );
	
	List<ExamenDTO> asExamenDTOs( List<Examen> examenes );
	
	@Named("formatCodigoExamen")
	public static String formatCodigoExamen( Integer examenId ) {
		
		String prefijo = "EX";
		
		GregorianCalendar grego = new GregorianCalendar();
		
		String anio = new SimpleDateFormat("yy").format(grego.getTime());
		String mes = new SimpleDateFormat("MM").format(grego.getTime());
		String dia = new SimpleDateFormat("dd").format(grego.getTime());
		
		prefijo += anio +""+ mes +""+ dia;
		
		String numero = "00000" + examenId;
		
		numero = numero.substring( numero.length() - 5, numero.length());
		
		return prefijo + numero;
	}
}
