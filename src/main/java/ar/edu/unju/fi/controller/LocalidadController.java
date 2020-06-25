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

import ar.edu.unju.fi.model.Localidad;
import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.service.ILocalidadService;
import ar.edu.unju.fi.service.IUsuarioService;


@Controller
public class LocalidadController {

@Autowired
ILocalidadService localidadServiceImp;

@Autowired
Localidad localidad;

//@GetMapping("/adminLocalidad")
//public String mostrarLocalidad(Model model) {
//	model.addAttribute("localidad", localidad);
//	return "adminLocalidad";
//}

@GetMapping("/adminLocalidad")
public String mostrarListaLocalidad (Model model) {
	model.addAttribute("localidad", localidad);
	List <Localidad> localidades = localidadServiceImp.listarlocalidad();
	model.addAttribute("localidades", localidades);
	//al atributo equipos creado le asigno la lista usuarios, el nombre de este va a la vista en th each
	return "adminLocalidad";
}

@PostMapping("/saveLoc")
public String guardarLocalidad (@Valid Localidad localidad, Model model) {
	localidadServiceImp.guardarLocalidad(localidad);
	return "redirect:/adminLocalidad";
}

@GetMapping("/editarLoc/{id}")
public String editar(@PathVariable Long id, Model model) {
	Optional<Localidad>usuario=localidadServiceImp.EditarLoc(id);
	model.addAttribute("usuario", usuario);
	return "adminLocalidad";
}

@GetMapping("/eliminarLoc/{id}")
public String eliminar (@PathVariable Long id, Model model) {
	localidadServiceImp.eliminarLocalidad(id);
	return "redirect:/adminLocalidad";
}



	
}
