package ar.edu.unju.fi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import ar.edu.unju.fi.model.Usuario;

public interface IUsuarioService {
	public void guardarUsuario(Usuario usuario);
	
	public List <Usuario> listarUsuario ();
	public Optional <Usuario> listarId(Long id);
	public void eliminarUsuario(Long id);
	public List <Usuario> findByTipoUsuario (String tipoUsuario);	
}
