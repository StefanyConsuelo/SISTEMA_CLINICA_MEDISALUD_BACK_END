package com.sidet.idat.ws.medisalud.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

import com.sidet.idat.ws.medisalud.entity.DetalleMedicacion;

@Mapper
public interface DetalleMedicacionRepository {

	@Insert("INSERT INTO tb_detalle_medicacion (frecuencia, dosis, via, siguiente_dosis, indicaciones, medicacion_id, medicamento_id, duracion) " + 
			"VALUES(#{frecuencia}, #{dosis}, #{via}, #{siguienteDosis}, #{indicaciones}, #{medicacion.medicacionId}, #{medicamento.medicamentoId},#{duracion});")
	@SelectKey( statement="SELECT LAST_INSERT_ID() as detalleMedicacionId", keyProperty="detalleMedicacionId", 
		keyColumn = "detalle_medicacion_id", resultType = int.class, before = false )
	void registrar( DetalleMedicacion detalleMedicacion );
	
}
