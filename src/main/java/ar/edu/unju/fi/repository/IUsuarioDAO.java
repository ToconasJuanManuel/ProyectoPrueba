package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.edu.unju.fi.model.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long>{
	
	//De la entidad Usuario traeme la lista ordena por nombre y retorna en la lista
	@Query("from Usuario u order by u.nombreUsuario")
	public List <Usuario> listarUsuario ();
	
	public List <Usuario> findByTipoUsuario (String tipoUsuario);
	
	

}
