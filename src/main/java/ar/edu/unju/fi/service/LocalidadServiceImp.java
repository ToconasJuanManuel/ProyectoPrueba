package ar.edu.unju.fi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Localidad;
import ar.edu.unju.fi.repository.ILocalidadDAO;

@Service
public class LocalidadServiceImp implements ILocalidadService {
	
	@Autowired
	ILocalidadDAO locDaoImp;


	@Override
	public List<Localidad> listarlocalidad() {
		return locDaoImp.listarLocalidad();
	}

	@Override
	public void guardarLocalidad(Localidad localidad) {
		locDaoImp.save(localidad);
	}

	@Override
	public Optional<Localidad> EditarLoc (Long id) {
		return locDaoImp.findById(id);
	}

	@Override
	public void eliminarLocalidad(Long id) {
		locDaoImp.deleteById(id);
		
	}

}
