package com.sidet.idat.ws.medisalud.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sidet.idat.ws.medisalud.entity.dto.ModuloDTO;
import com.sidet.idat.ws.medisalud.exceptions.AccesoDenegadoException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -6043273011540746883L;
	
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer";
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	public static final String JWT_SECRET = "Jwt_secret_123";
	
	public String generarToken( Claims claims, String username ) {
		String token = Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + JwtTokenUtil.JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, JwtTokenUtil.JWT_SECRET).compact();
		return token;
	}
	
	public List<SimpleGrantedAuthority> getRoles( String token ){
		List<SimpleGrantedAuthority> roles=new ArrayList<SimpleGrantedAuthority>();
		Claims claims = this.getClaims(token);
		List<ModuloDTO> dto = null;
		try {
			dto = new ObjectMapper().readValue(claims.get("modulos").toString(), new TypeReference<List<ModuloDTO>>(){} );
		} catch (JsonMappingException e) {
			throw new AccesoDenegadoException("No se encontraron permisos, acceso prohibido", e);
		} catch (JsonProcessingException e) {
			throw new AccesoDenegadoException("No se encontraron permisos, acceso prohibido", e);
		}
		//log.info("listado de roles permitidos para el usuario");
		for (ModuloDTO moduloDTO : dto) {
			List<String> rolesString = moduloDTO.getRoles();
			for (String rolString : rolesString) {
				//log.info("rol {}", rolString);
				roles.add( new SimpleGrantedAuthority(rolString));
			}
		}
		return roles;
	}
	
	public String getToken( HttpServletRequest request ) {
		String jwtToken = request.getHeader(JwtTokenUtil.HEADER_AUTHORIZACION_KEY).replace(JwtTokenUtil.TOKEN_BEARER_PREFIX, "");
		//log.info("token de acceso:{}", jwtToken);
		if( jwtToken == null ) {
			throw new AccesoDenegadoException("No se encontró el token de acceso, acceso prohibido");
		}
		return jwtToken;
	}
	
	private Date obtenerExpiracionFechaDesdeToken(String token) {
		return getClaimDesdeToken(token, Claims::getExpiration);
	}

	private <T> T getClaimDesdeToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = this.getClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(JwtTokenUtil.JWT_SECRET).parseClaimsJws(token).getBody();
	}
	
	private Boolean tokenExpirado(String token) {
		final Date expiration = obtenerExpiracionFechaDesdeToken(token);
		return expiration.before(new Date());
	}
	
	public Boolean validarToken( String token ) {
		return this.getUsernameDesdeToken(token) != null && !this.tokenExpirado(token);
	}
	
	public String getUsernameDesdeToken( String token ) {
		return Jwts.parser()
			.setSigningKey(JwtTokenUtil.JWT_SECRET)
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}
}
