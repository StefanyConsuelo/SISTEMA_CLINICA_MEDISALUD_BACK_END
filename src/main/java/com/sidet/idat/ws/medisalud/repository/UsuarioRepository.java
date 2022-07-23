package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sidet.idat.ws.medisalud.entity.Modulo;
import com.sidet.idat.ws.medisalud.entity.Usuario;

@Mapper
public interface UsuarioRepository {
	
	@Results(value = {
		@Result(property = "usuarioId", column = "usuario_id"),
		@Result(property = "perfil.perfilId", column = "perfil_id"),
		@Result(property = "perfil.nombre", column = "perfil_nombre"),
		@Result(property = "persona.personaId", column = "persona_id"),
		@Result(property = "persona.nombres", column = "nombres"),
		@Result(property = "persona.apellidoPaterno", column = "apellido_paterno"),
		@Result(property = "persona.apellidoMaterno", column = "apellido_materno")
	})
	@Select(" select tu.*, tp.nombres, tp.apellido_paterno, tp.apellido_materno, tp2.nombre as perfil_nombre from tb_usuario tu" + 
			" inner join tb_persona tp on tp.persona_id = tu.persona_id "+
			" inner join tb_perfil tp2 on tp2.perfil_id = tu.perfil_id " +
			" where username = #{username}")
	Usuario buscar( String username );
	
	@Results( value = {
		@Result( property = "rolesAgrupados", column = "roles_agrupados" )
	})
	@Select("select tm.nombre, group_concat(tr.nombre) as 'roles_agrupados', tm.codigo from tb_usuario tu " + 
			"inner join tb_perfil_rol tpr on tpr.perfil_id = tu.perfil_id " + 
			"inner join tb_rol tr on tr.rol_id = tpr.rol_id " + 
			"inner join tb_modulo tm on tm.modulo_id = tr.modulo_id " + 
			"where usuario_id = #{usuarioId} " + 
			"group by tm.modulo_id;")
	List<Modulo> buscarRoles( Integer usuarioId );
	
	@Insert("INSERT INTO tb_usuario (username, password, enabled, perfil_id, persona_id) "
			+ "VALUES(#{username}, #{password}, 1, #{perfil.perfilId}, #{persona.personaId});")
	@SelectKey(statement="SELECT LAST_INSERT_ID() as usuarioId", keyProperty="usuarioId", keyColumn = "usuario_id", resultType = int.class, before = false )
	void registrar( Usuario usuario );
	
	@Update("UPDATE tb_usuario SET username=#{username}, perfil_id=#{perfil.perfilId} WHERE usuario_id=#{usuarioId};")
	void actualizar( Usuario usuario );
	
	@Delete("UPDATE tb_usuario SET activo = 0 WHERE usuario_id=#{usuarioId};")
	void eliminar( Integer usuarioId );
	
	@Results( value = {
		@Result( property = "usuarioId", column = "usuario_id" ),
		@Result( property = "perfil.perfilId", column = "perfil_id" ),
		@Result( property = "perfil.nombre", column = "perfil" ),
		@Result( property = "persona.personaId", column = "persona_id" ),
		@Result( property = "persona.nombres", column = "nombres" ),
		@Result( property = "persona.apellidoPaterno", column = "apellido_paterno" ),
		@Result( property = "persona.apellidoMaterno", column = "apellido_materno" )
	}, id = "resultUsuarioV1")
	@Select(" select u.usuario_id, u.username, u.enabled, u.activo, " + 
			" tp.perfil_id, tp.nombre as 'perfil', tp2.persona_id, tp2.nombres, " + 
			" tp2.apellido_paterno, tp2.apellido_materno " + 
			" from tb_usuario u " + 
			" inner join tb_perfil tp on tp.perfil_id = u.perfil_id " + 
			" inner join tb_persona tp2 on tp2.persona_id = u.persona_id " + 
			" where u.usuario_id = #{usuarioId};")
	Usuario buscarPorId( Integer usuarioId );
	
	
	@ResultMap("resultUsuarioV1")
	@Select(" select u.usuario_id, u.username, u.enabled, u.activo, " + 
			" tp.perfil_id, tp.nombre as 'perfil', tp2.persona_id, tp2.nombres, " + 
			" tp2.apellido_paterno, tp2.apellido_materno " + 
			" from tb_usuario u " + 
			" inner join tb_perfil tp on tp.perfil_id = u.perfil_id " + 
			" inner join tb_persona tp2 on tp2.persona_id = u.persona_id " +
			" where u.activo = 1" +
			" order by usuario_id desc" + 
			" limit #{inicio},#{fin}")
	List<Usuario> listarPaginado( Integer inicio, Integer fin );
	
	@Select("select count(*) as total_filas from tb_usuario tu where activo = 1;")
	Integer contarTotalFilas();
	
	@Update("UPDATE tb_usuario SET enabled=0 WHERE usuario_id=#{usuarioId};")
	void bloquear( Integer usuarioId );
	
	@Update("UPDATE tb_usuario SET enabled=1 WHERE usuario_id=#{usuarioId};")
	void desbloquear( Integer usuarioId );
	
	@Select("select 1 from tb_usuario tu where tu.persona_id = #{personaId} and tu.activo = 1;")
	Integer validarSiTieneUsuario( Integer personaId );
	
	@Select("select 1 from tb_usuario tu where tu.username = #{username}")
	Integer validarSiNombreUsuarioExiste( String username );
}
