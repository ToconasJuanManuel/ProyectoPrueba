/**
 * @author Velarde Marcia
 * 
 */

package ar.edu.unju.fi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.fi.model.Localidad;
import ar.edu.unju.fi.model.RegistroTracking;
import ar.edu.unju.fi.service.ILocalidadService;
import ar.edu.unju.fi.service.IRegistroTrackingService;
import ar.edu.unju.fi.service.RegistroTrackingServiceImp;

@Controller
public class RegistradorController {
	
	
	@Autowired
	private ILocalidadService localidadService;
	
	@Autowired 
	private RegistroTrackingServiceImp RegImp;
	
	@Autowired
	private RegistroTracking r;
	
	@GetMapping("/registrador")
	public String registradorVehiculo(Model model) {
		model.addAttribute("listaLocalidades", localidadService.listarLocalidades());
		model.addAttribute("registros", r);
		return "registradorVehiculos";
	}
	
	@GetMapping("/regvis")
	public String registradorVistas(Model model) {
		return "registradorVista";
	}
	
	@PostMapping("/saveReg")
	public String crearRegistro(@Valid RegistroTracking r, Model model) {
		model.addAttribute("registros", r);
		RegImp.guardarR(r);
		return "redirect:/registrador";
	}
}
