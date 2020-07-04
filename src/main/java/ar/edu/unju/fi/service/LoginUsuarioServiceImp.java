package ar.edu.unju.fi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.repository.IUsuarioDAO;

//implementa UserDetailsService que trae SpringSecurity
//recupera el usuario con el que se va a trabajar
//Provee de 3 acciones con las que trabajar
//1-verificar el usuario
//2-mostrar el tipo de usuario
//3-Encriptacion de clave de usuario

@Service
public class LoginUsuarioServiceImp implements UserDetailsService {
	@Autowired
	IUsuarioDAO usuarioImp;

	//Metodo de UserDetailService que recibe un DNI y si hay problemas pasa por una excepcion 
	//El UserDetail devuelve un usuario especial de su tipo homonimo
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Se busca al usuario y si no se encuentra devuelve un mensaje de error de login (Paso 1)
		Usuario encontrado = usuarioImp.findByNombreUsuario(username).orElseThrow(()-> new UsernameNotFoundException("Login Invalido"));
		
		//Listado granted que guarda el tipo de usuario en una lista que recibe el tipo del usuario encontrado
		List<GrantedAuthority> tipos = new ArrayList<>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(encontrado.getTipoUsuario());
		tipos.add(grantedAuthority);
		
		//No devuelvo el usuario encontrado sino algunos campos del mismo (PASO 2 DEFINIR EL RETURN USER CON SUS DATOS PERO FALTA CREAR LISTA DE ROLES que es tipos)
		//SE CREA USER DE SECURITY
		UserDetails user = (UserDetails) new User(username, encontrado.getPassword(), tipos);
	return user;
	}
}
