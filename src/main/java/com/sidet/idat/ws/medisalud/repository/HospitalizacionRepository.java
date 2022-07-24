package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sidet.idat.ws.medisalud.entity.Hospitalizacion;

@Mapper
public interface HospitalizacionRepository {

	@Insert( "INSERT INTO tb_hospitalizacion( pre_diagnostico, procedencia, observacion, alta, estado, paciente_id, personal_medico_id, cama_id )"
			 + " VALUES( #{preDiagnostico}, #{procedencia}, #{observacion}, #{alta}, #{estado}, #{paciente.pacienteId},"
			 + " #{personalMedico.personalMedicoId}, #{cama.camaId} )" )
	@SelectKey( statement="SELECT LAST_INSERT_ID() as hospitalizacionId", keyProperty="hospitalizacionId", keyColumn = "hospitalizacion_id", 
	resultType = int.class, before = false )
	void registrar( Hospitalizacion hospitalizacion );
	
	@Results( value = {
		@Result(property = "hospitalizacionId", column = "hospitalizacion_id"),
		@Result(property = "fechaRegistro", column = "fecha_registro_hospitalizacion"),
		@Result(property = "preDiagnostico", column = "pre_diagnostico"),
		@Result(property = "cama.camaId", column = "cama_id"),
		@Result(property = "cama.nombre", column = "cama_nombre"),
		@Result(property = "paciente.pacienteId", column = "paciente_id"),
		@Result(property = "paciente.nombres", column = "paciente_nombres"),
		@Result(property = "paciente.apellidoPaterno", column = "paciente_apellido_paterno"),
		@Result(property = "paciente.apellidoMaterno", column = "paciente_apellido_materno"),
		@Result(property = "paciente.historiaClinica.historiaClinicaId", column = "historia_clinica_id"),
		@Result(property = "personalMedico.personalMedicoId", column = "personal_medico_id"),
		@Result(property = "personalMedico.nombres", column = "personal_medico_nombres"),
		@Result(property = "personalMedico.apellidoPaterno", column = "personal_medico_apellido_paterno"),
		@Result(property = "personalMedico.apellidoMaterno", column = "personal_medico_apellido_materno"),
		@Result(property = "personalMedico.servicio.servicioId", column = "servicio_id"),
		@Result(property = "personalMedico.servicio.nombre", column = "servicio_nombre")
	})
	@Select(" SELECT H.hospitalizacion_id, H.fecha_registro as fecha_registro_hospitalizacion, H.pre_diagnostico, H.procedencia, H.observacion," + 
			" H.alta, H.estado, C.cama_id, C.nombre as cama_nombre, P.paciente_id, Per.nombres as paciente_nombres," + 
			" Per.apellido_paterno as paciente_apellido_paterno, Per.apellido_materno as paciente_apellido_materno, PM.personal_medico_id, " + 
			" Per1.nombres as personal_medico_nombres, Per1.apellido_paterno as personal_medico_apellido_paterno," + 
			" Per1.apellido_materno as personal_medico_apellido_materno, S.servicio_id, S.nombre as servicio_nombre, thc.historia_clinica_id" + 
			" FROM tb_hospitalizacion H" + 
			" INNER JOIN tb_paciente P ON P.paciente_id = H.paciente_id" + 
			" INNER JOIN tb_persona Per ON Per.persona_id = P.persona_id" + 
			" INNER JOIN tb_cama C ON C.cama_id = H.cama_id" + 
			" INNER JOIN tb_personal_medico PM on PM.personal_medico_id = H.personal_medico_id" + 
			" INNER JOIN tb_persona Per1 ON Per1.persona_id = PM.persona_id" + 
			" INNER JOIN tb_servicio S ON S.servicio_id = PM.servicio_id" + 
			" inner join tb_historia_clinica thc on thc.paciente_id = P.paciente_id" + 
			" WHERE H.estado = 'activo';" )
	List<Hospitalizacion> listarTodos();
	
	@Update("UPDATE tb_hospitalizacion SET alta = 'SI' WHERE hospitalizacion_id = #{hospitalizacionId}")
	void darAlta( Integer hospitalizacionId );
	
	@Update("UPDATE tb_cama SET estado = 'Disponible' WHERE cama_id = #{camaId}")
	void darDisponibilidadCama( Integer camaId );
	
	@Update("UPDATE tb_hospitalizacion SET estado = 'finalizado' WHERE hospitalizacion_id = #{hospitalizacionId}")
	void finalizarHospitalizacion( Integer hospitalizacionId );
}
