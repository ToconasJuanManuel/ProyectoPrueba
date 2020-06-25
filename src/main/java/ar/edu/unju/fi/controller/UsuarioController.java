package ar.edu.unju.fi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;


@Controller
public class UsuarioController {

@Autowired
IUsuarioService usuarioServiceImp;

@Autowired
Usuario usuario;

@GetMapping("/adminPrincipal")
public String mostrarListaAdmin(Model model) {
	List <Usuario> usuarios = usuarioServiceImp.listarUsuario();
	model.addAttribute("usuarios", usuarios);
	//al atributo equipos creado le asigno la lista usuarios, el nombre de este va a la vista en th each
	return "adminPrincipal";
}

@GetMapping("/adminFormulario")
public String mostrarFormAdmin(Model model) {
	model.addAttribute("usuario", usuario);
	return "adminFormulario";
}

@PostMapping("/save")
public String guardar(@Valid Usuario usuario, Model model) {
	usuarioServiceImp.guardarUsuario(usuario);
	return "redirect:/adminPrincipal";
}

@GetMapping("/editar/{id}")
public String editar(@PathVariable Long id, Model model) {
	Optional<Usuario>usuario=usuarioServiceImp.listarId(id);
	model.addAttribute("usuario", usuario);
	return "adminFormulario";
}

@GetMapping("/eliminar/{id}")
public String eliminar (@PathVariable Long id, Model model) {
	usuarioServiceImp.eliminarUsuario(id);
	return "redirect:/adminPrincipal";
}

@GetMapping("/adminRegistrador")
public String mostrarRegistrador(Model model) {
	List <Usuario> usuarios = usuarioServiceImp.findByTipoUsuario("REGISTRADOR");
	model.addAttribute("usuarios", usuarios);	
	return "adminRegistrador";
}

@GetMapping("/adminConsultor")
public String mostrarConsultorConsul(Model model) {
	List <Usuario> usuarios = usuarioServiceImp.findByTipoUsuario("CONSULTOR");
	model.addAttribute("usuarios", usuarios);
	return "adminConsultor";
}
	
}
