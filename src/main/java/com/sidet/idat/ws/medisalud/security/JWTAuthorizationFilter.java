package com.sidet.idat.ws.medisalud.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JwtTokenUtil jwtTokenUtil;

	public JWTAuthorizationFilter(AuthenticationManager authManager, JwtTokenUtil jwtTokenUtil ) {
		super(authManager);
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String header = request.getHeader(JwtTokenUtil.HEADER_AUTHORIZACION_KEY);
		if (header == null || !header.startsWith(JwtTokenUtil.TOKEN_BEARER_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		
		String token = jwtTokenUtil.getToken(request);
		
		log.info("verficando la authorizacion del usuario");
		if (token != null) {
			log.info("recuperando el usuario authenticado para procesar la solicitud ");
			String user = jwtTokenUtil.getUsernameDesdeToken(token);
			
			if (user != null) {
				log.info("usuario ({}) authenticado", user);
				
				List<SimpleGrantedAuthority> roles= jwtTokenUtil.getRoles(token);
				
				return new UsernamePasswordAuthenticationToken(user, null, roles);
			}
			return null;
		}
		return null;
	}
}
