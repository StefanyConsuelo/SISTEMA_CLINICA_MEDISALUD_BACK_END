package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sidet.idat.ws.medisalud.entity.DetalleMedicacion;
import com.sidet.idat.ws.medisalud.entity.Medicacion;

@Mapper
public interface MedicacionRepository {
	
	@Results( value = {
		@Result(property = "detalleMedicacionId", column = "detalle_medicacion_id"),
		@Result(property = "siguienteDosis", column = "siguiente_dosis"),
		@Result(property = "medicamento.medicamentoId", column = "medicamento_id"),
		@Result(property = "medicamento.nombre", column = "medicamento_nombre"),
		@Result(property = "medicacion.medicacionId", column = "medicacion_id"),
		@Result(property = "medicacion.historiaClinica.historiaClinicaId", column = "historia_clinica_id")
	})
	@Select( "SELECT DM.detalle_medicacion_id, DM.frecuencia, DM.dosis, DM.via, DM.siguiente_dosis, DM.indicaciones, DM.duracion, "
			+ "Med.medicamento_id, Med.nombre as medicamento_nombre, M.medicacion_id, M.historia_clinica_id FROM tb_detalle_medicacion DM "
			+ "INNER JOIN tb_medicacion M ON M.medicacion_id = DM.medicacion_id "
			+ "INNER JOIN tb_medicamento Med ON Med.medicamento_id = DM.medicamento_id WHERE M.historia_clinica_id = #{historiaClinicaId} "
			+ "AND M.fecha_registro = ( SELECT MAX(M1.fecha_registro) FROM tb_medicacion M1 WHERE M1.historia_clinica_id = #{historiaClinicaId} );")
	List<DetalleMedicacion> listarUltimaPorHistoriaClinicaId( Integer historiaClinicaId );
	
	@Results( value = {
		@Result(property = "detalleMedicacionId", column = "detalle_medicacion_id"),
		@Result(property = "siguienteDosis", column = "siguiente_dosis")
	})
	@Select( "SELECT DM.detalle_medicacion_id, DM.siguiente_dosis FROM tb_detalle_medicacion DM WHERE DM.detalle_medicacion_id = #{detalleMedicacionId};" )
	DetalleMedicacion buscarDetalleMedicacionPorId( Integer detalleMedicacionId );
	
	@Update( "UPDATE tb_detalle_medicacion SET siguiente_dosis = #{siguienteDosis} WHERE detalle_medicacion_id = #{detalleMedicacionId}" )
	void establecerSiguienteDosis( Integer siguienteDosis, Integer detalleMedicacionId );
	
	@Insert("INSERT INTO tb_medicacion (historia_clinica_id, personal_medico_id) "
			+ " VALUES(#{historiaClinica.historiaClinicaId}, #{personalMedico.personalMedicoId});")
	@SelectKey( statement="SELECT LAST_INSERT_ID() as medicacionId", keyProperty="medicacionId", 
		keyColumn = "medicacion_id", resultType = int.class, before = false )
	void registrar( Medicacion medicacion );
	
	@Results( value = {
		@Result(property = "fechaRegistro", column = "fecha_registro"),
		@Result(property = "personalMedico.nombres", column = "nombres"),
		@Result(property = "personalMedico.apellidoPaterno", column = "apellido_paterno"),
		@Result(property = "personalMedico.apellidoMaterno", column = "apellido_materno"),
		@Result(property = "detalle", column = "medicacion_id", many = @Many( select = "listarDetalleMedicacion" ))
	})
	@Select( " SELECT tm.fecha_registro, tm.medicacion_id, tp.nombres, tp.apellido_paterno, tp.apellido_materno FROM tb_medicacion tm\r\n" + 
			" inner join tb_personal_medico tpm on tpm.personal_medico_id = tm.personal_medico_id \r\n" + 
			" inner join tb_persona tp on tp.persona_id = tpm.persona_id "
			+ " where historia_clinica_id = #{historiaClinicaId} "
			+ " ORDER BY fecha_registro desc LIMIT #{inicio},#{fin}" )
	List<Medicacion> listarPaginado( Integer inicio, Integer fin, Integer historiaClinicaId );
	
	@Select("SELECT count(fecha_registro) as total_filas FROM tb_medicacion where historia_clinica_id = #{historiaClinicaId} ")
	Integer contarTotalFilas( Integer historiaClinicaId );
	
	@Results( value = {
		@Result( property = "siguienteDosis", column = "siguiente_dosis" ),
		@Result( property = "medicamento.nombre", column = "medicamento" ),
		@Result( property = "medicamento.medicamentoId", column = "medicamento_id" )
	})
	@Select(" select tdm.*, tm.nombre as medicamento from tb_detalle_medicacion tdm" + 
			" inner join tb_medicamento tm on tm.medicamento_id = tdm.medicamento_id " + 
			" where tdm.medicacion_id = #{medicacionId}")
	List<DetalleMedicacion> listarDetalleMedicacion( Integer medicacionId );
}
