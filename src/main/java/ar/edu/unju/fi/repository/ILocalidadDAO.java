package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.edu.unju.fi.model.Localidad;


public interface ILocalidadDAO extends JpaRepository<Localidad, Long>{
	//De la entidad Usuario traeme la lista ordena por nombre y retorna en la lista
		@Query("from Localidad l order by l.nombre")
		public List <Localidad> listarLocalidad ();
}
