package com.sidet.idat.ws.medisalud.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sidet.idat.ws.medisalud.entity.Usuario;
import com.sidet.idat.ws.medisalud.entity.dto.JwtAuthenticacionDTO;
import com.sidet.idat.ws.medisalud.entity.dto.ModuloDTO;
import com.sidet.idat.ws.medisalud.exceptions.AccesoDenegadoException;
import com.sidet.idat.ws.medisalud.services.impl.UsuarioDetailsServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private UsuarioDetailsServiceImpl usuarioDetailsService;
	private AuthenticationManager authenticationManager;
	private JwtTokenUtil jwtTokenUtil;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, 
			UsuarioDetailsServiceImpl usuarioDetailsService, JwtTokenUtil jwtTokenUtil) {
		this.authenticationManager = authenticationManager;
		this.usuarioDetailsService = usuarioDetailsService;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			JwtAuthenticacionDTO credenciales = new ObjectMapper()
					.readValue(request.getInputStream(), JwtAuthenticacionDTO.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					credenciales.getUsername(), credenciales.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		log.info("authenticacion correcta, generando token de acceso para el usuario {}", authResult.getName());
		
		Usuario usuario = usuarioDetailsService.buscarUsuario(authResult.getName());
		
		List<ModuloDTO> modulos = usuario.getModulos().stream()
			.map( m -> {
				ModuloDTO dto = new ModuloDTO();
				dto.construirRoles(m.getRolesAgrupados());
				dto.setNombre(m.getNombre());
				dto.setCodigo(m.getCodigo());
				return dto;
			}).collect(Collectors.toList());
		
		Claims claims = Jwts.claims();
		claims.put("modulos", new ObjectMapper().writeValueAsString(modulos));
		claims.put("personaId", usuario.getPersona().getPersonaId());
		claims.put("perfilId", usuario.getPerfil().getPerfilId());
		claims.put("nombres", usuario.getPersona().getNombres());
		claims.put("apellidoPaterno", usuario.getPersona().getApellidoPaterno());
		claims.put("apellidoMaterno", usuario.getPersona().getApellidoMaterno());
		claims.put("nombrePerfil", usuario.getPerfil().getNombre());
		
		String token = jwtTokenUtil.generarToken(claims, authResult.getName());
		
		response.getWriter().write( new ObjectMapper().writeValueAsString( new JwtResponse(token) ));
	} 
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		log.info("authenticacion invalida");
		throw new AccesoDenegadoException("Usuario y contraseña incorrecto");
	}
}
