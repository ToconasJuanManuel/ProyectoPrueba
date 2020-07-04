package ar.edu.unju.fi;

//Interfaz que redirige a ciertas paginas segun el tipo de usuario

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AutenticacionSuccessHandler implements AuthenticationSuccessHandler{

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy(); 
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
			boolean tipoBD = false;
			boolean tipoConsultor = false;
			boolean tipoRegistrador = false;
			//Se prueba con el Collection de java pero cuidado que puede cambiar
			Collection <? extends GrantedAuthority > authorities = authentication.getAuthorities();
			for (GrantedAuthority grantedAuthority : authorities) {
				if (grantedAuthority.getAuthority().equals("BD")) {
					tipoBD = true;
					break;
				}else {
					if(grantedAuthority.getAuthority().equals("CONSULTOR")) {
						tipoConsultor = true;
						break;
					}else {
						if(grantedAuthority.getAuthority().equals("REGISTRADOR")) {
						tipoRegistrador = true;
						break;
						}
					}
				}
			}
			
			if (tipoBD) {
				redirectStrategy.sendRedirect(request, response, "/adminFormulario");
			}else {
				if(tipoConsultor) {
					redirectStrategy.sendRedirect(request, response, "/patente");
				}else {
					if(tipoRegistrador) {
						redirectStrategy.sendRedirect(request, response, "/registrador");
					}else {
						throw new IllegalStateException();	
					}
						}
					}
				}
			}
