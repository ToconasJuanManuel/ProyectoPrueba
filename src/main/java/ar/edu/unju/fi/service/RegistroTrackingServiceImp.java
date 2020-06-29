package ar.edu.unju.fi.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.RegistroTracking;
import ar.edu.unju.fi.repository.IRegistroTrackingDAO;



@Service
public class RegistroTrackingServiceImp implements IRegistroTrackingService {

	@Autowired
	private IRegistroTrackingDAO regDao;
	
	@Override
	public void guardarR(RegistroTracking reg) {
	LocalDateTime hora = LocalDateTime.now();
	reg.setFechaHora(hora);
	regDao.save(reg);
	}

}
