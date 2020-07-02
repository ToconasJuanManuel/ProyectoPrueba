package ar.edu.unju.fi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.AuthenticationManagerBeanDefinitionParser;
//Se indica que es y que no es seguro hacer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ar.edu.unju.fi.service.LoginUsuarioServiceImp;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AutenticacionSuccessHandler autenticacion;
	
	String [] resources = new String[] {
		"/include/**","/css/**","/icons/**","/images/**","/js/**","/layer/**","/webjars/**",
		//frag, carpetas
	};
	
	
	//Se SobreEscribe el metodo configure
	//Recibe una peticion de tipo HTTP
	@Override
	protected void configure (HttpSecurity http) throws Exception{
		//A esa peticion se autoriza 
		http
			.authorizeRequests()
				.antMatchers(resources).permitAll() //los recursos de arriba
				.antMatchers("/","home").permitAll() // requetimiento a slash y slash home
				//.antMatchers("/adminFormulario").hasRole("BD") //url que no quiero permitir o permitir en determinado rol
				//.antMatchers("/buscarPatente").hasRole("CONSULTOR") 
				//.antMatchers("/adminFormulario").not().hasAuthority("ROLE_CANDIDATE")
				//.antMatchers("/adminFormulario","/adminPrincipal","/adminRegistrador","/adminLocalidad").not().hasAuthority("CONSULTOR")
				//.antMatchers("/adminFormulario","/adminPrincipal","/adminRegistrador","/adminLocalidad","/vehiculo","/tripulante","/patente").not().hasAuthority("REGISTRADOR")
				//.antMatchers("/adminFormulario","/adminPrincipal","/adminRegistrador","/adminLocalidad","/registrador").not().hasAuthority("CONSULTOR")
				//.antMatchers("/registrador","/vehiculo","/tripulante","/patente").not().hasAuthority("BD")
				//.antMatchers("/adminFormulario").access("hasRole('ROLE_BD')")
				//.antMatchers("/userPage").access("hasRole('ROLE_USER')")
				//.antMatchers("/adminPage").access("hasRole('ROLE_ADMIN')")
				
				
				.anyRequest().authenticated() //todo lo demas requiere autenticacion
				.and()
			.formLogin()
				.loginPage("/login") //La pagina principal es login2 y todos tienen acceso
				.permitAll()
				.successHandler(autenticacion) //donde redirige en caso de exito
				.failureUrl("/login?error=true") //donde rediroige en caso de error
				.usernameParameter("username")
				.passwordParameter("password")
				.and()
			.logout()
				.permitAll();
				//.logoutSuccessUrl("/login?logout")
			http.csrf().disable(); //impide que se puedan hacer fishing
	}
	
	//paso 2 -> encriptacion de la clave
	//varible que permite codificar la clave por metodo matematico
	BCryptPasswordEncoder BCryptPasswordEncoder;
	
	//codifica la clave
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		BCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		return BCryptPasswordEncoder;
	}
	
	@Autowired
	LoginUsuarioServiceImp loginImp;
	
	//metodo que recibe el managerbuild que recupera la informacion del usuariovque desea logearse
	//
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginImp).passwordEncoder(passwordEncoder());
	}
}
