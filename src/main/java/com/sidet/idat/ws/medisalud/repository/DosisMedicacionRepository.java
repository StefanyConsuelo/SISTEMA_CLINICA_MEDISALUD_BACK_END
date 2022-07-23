package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sidet.idat.ws.medisalud.entity.DosisMedicacion;

@Mapper
public interface DosisMedicacionRepository {

	@Results( value = {
		@Result( property = "dosisMedicacionId", column = "dosis_medicacion_id" ),
		@Result( property = "fechaDosis", column = "fecha_dosis" ),
		@Result( property = "detalleMedicacion.siguienteDosis", column = "siguiente_dosis" ),
		@Result( property = "estado", column = "estado_dosis" ),
		@Result( property = "numeroDosis", column = "numero_dosis" ),
		@Result( property = "detalleMedicacion.detalleMedicacionId", column = "detalle_medicacion_id" ),
		@Result( property = "detalleMedicacion.medicacion.medicacionId", column = "medicacion_id" ),
		@Result( property = "detalleMedicacion.medicamento.medicamentoId", column = "medicamento_id" ),
		@Result( property = "detalleMedicacion.medicamento.nombre", column = "medicamento_nombre" )
	})
	@Select(" SELECT DoM.dosis_medicacion_id, DoM.fecha_dosis, Max(DeM.siguiente_dosis) as siguiente_dosis, DoM.estado, " + 
			" DeM.detalle_medicacion_id, M.medicacion_id, Med.medicamento_id, Med.nombre as medicamento_nombre, DoM.numero_dosis " + 
			" FROM tb_dosis_medicacion DoM" + 
			" INNER JOIN tb_detalle_medicacion DeM ON DeM.detalle_medicacion_id = DoM.detalle_medicacion_id and DeM.siguiente_dosis = DoM.numero_dosis" + 
			" INNER JOIN tb_medicacion M ON M.medicacion_id = DeM.medicacion_id" + 
			" INNER JOIN tb_historia_clinica HC ON HC.historia_clinica_id = M.historia_clinica_id" + 
			" INNER JOIN tb_medicamento Med ON Med.medicamento_id = DeM.medicamento_id" + 
			" WHERE HC.historia_clinica_id = #{historiaClinicaId} " + 
			" and M.fecha_registro = ( select max(tm.fecha_registro) from tb_medicacion tm where tm.historia_clinica_id = #{historiaClinicaId})" + 
			" GROUP BY(DeM.detalle_medicacion_id)" )
	List<DosisMedicacion> listarSiguienteMedicacionPorHistoriaClinicaId( Integer historiaClinicaId );
	
	@Results( value = {
		@Result( property = "dosisMedicacionId", column = "dosis_medicacion_id" ),
		@Result( property = "numeroDosis", column = "numero_dosis" ),
		@Result( property = "detalleMedicacion.detalleMedicacionId", column = "detalle_medicacion_id" ),
		@Result( property = "detalleMedicacion.frecuencia", column = "frecuencia" )
	}, id = "resultDosisV1")
	@Select(" SELECT DM.dosis_medicacion_id, DM.estado, DM.detalle_medicacion_id, tdm.frecuencia, DM.numero_dosis FROM tb_dosis_medicacion DM" + 
			" inner join tb_detalle_medicacion tdm on tdm.detalle_medicacion_id = DM.detalle_medicacion_id " + 
			" WHERE DM.dosis_medicacion_id = #{dosisMedicacionId};" )
	DosisMedicacion buscarPorId( Integer dosisMedicacionId );
	
	@Update( "UPDATE tb_dosis_medicacion SET estado = 'realizado', fecha_dosis = #{fechaDosis} WHERE dosis_medicacion_id = #{dosisMedicacionId}" )
	void actualizarEstado( Integer dosisMedicacionId, String fechaDosis );
	
	@Insert("INSERT INTO tb_dosis_medicacion (numero_dosis, estado, detalle_medicacion_id) "
			+ " VALUES(#{numeroDosis}, #{estado}, #{detalleMedicacionId});")
	void registrar( Integer numeroDosis, String estado, Integer detalleMedicacionId );
	
	@ResultMap("resultDosisV1")
	@Select("select * from tb_dosis_medicacion tdm where tdm.detalle_medicacion_id = #{detalleMedicacionId} and tdm.dosis_medicacion_id > #{dosisMedicacionId}")
	List<DosisMedicacion> listarTodasSiguientesDosis( Integer detalleMedicacionId, Integer dosisMedicacionId );
	
	@Update("update tb_dosis_medicacion set fecha_dosis = #{fechaDosis} where dosis_medicacion_id = #{dosisMedicacionId}")
	void actualizarFechaDosis( String fechaDosis, Integer dosisMedicacionId );
		
	List<Integer> alertarDosisPacientes( List<Integer> pacientesIds );
}
