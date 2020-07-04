package ar.edu.unju.fi.tracking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.service.UsuarioServiceImp;

@SpringBootTest
class ProyectoPrueba1ApplicationTests {

	@Autowired
	UsuarioServiceImp imp;
	
	
	@Test
	void guardarUsuario() {
		//Usuario usuario = new Usuario();
		//usuario.setApellidoReal("A");
		//usuario.setNombreReal("A");
		//usuario.setNombreUsuario("BD");
		//usuario.setPassword("1234");
		//usuario.setTipoUsuario("BD");
		
		/*
		Usuario usuario2 = new Usuario();
		usuario2.setApellidoReal("B");
		usuario2.setNombreReal("B");
		usuario2.setNombreUsuario("CON");
		usuario2.setPassword("1234");
		usuario2.setTipoUsuario("CONSULTOR");
		imp.crear(usuario2);
		*/
		
		
		Usuario usuario4 = new Usuario();
		usuario4.setApellidoReal("George");
		usuario4.setNombreReal("George");
		usuario4.setNombreUsuario("G");
		usuario4.setPassword("1234");
		usuario4.setTipoUsuario("REGISTRADOR");
		imp.crear(usuario4);
		
	}
	
}
