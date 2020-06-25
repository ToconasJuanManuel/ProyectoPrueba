package ar.edu.unju.fi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.repository.IUsuarioDAO;

@Service
public class UsuarioServiceImp implements IUsuarioService {

	//La capa de servicio necesita establecer comunicacion con la interfaz del repositorio asi que se autoinyecta una objeto de este tipo
	@Autowired
	IUsuarioDAO usuarioDaoImp;
	
	@Override
	public void guardarUsuario(Usuario usuario) {
		usuarioDaoImp.save(usuario);
	//Se usan los metodos del CRUD repository . save permite guardar si se pasa el metodo del parametro
	}

	@Override
	public List<Usuario> listarUsuario() {
		//return usuarioDaoImp.findAll();
		return usuarioDaoImp.listarUsuario();
	}

	@Override
	public Optional<Usuario> listarId(Long id) {
		return usuarioDaoImp.findById(id);
	}

	@Override
	public void eliminarUsuario(Long id) {
		usuarioDaoImp.deleteById(id);
		
	}

	@Override
	public List<Usuario> findByTipoUsuario(String tipoUsuario) {		
		return usuarioDaoImp.findByTipoUsuario(tipoUsuario) ;
	}

	

}
