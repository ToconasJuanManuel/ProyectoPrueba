package ar.edu.unju.fi.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.model.Localidad;

public interface ILocalidadService {
	
	public void guardarLocalidad (Localidad localidad);
	public List <Localidad> listarlocalidad ();
	public Optional <Localidad> EditarLoc(Long id);
	public void eliminarLocalidad (Long id);
}
