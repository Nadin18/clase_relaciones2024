package py.edu.facitec.repository;

import org.springframework.data.jpa.repository.JpaRepository; 

import py.edu.facitec.model.Suscrito;
                                      //Entidad y el tipo de datos del pk
public interface SuscritoRepository extends JpaRepository<Suscrito, Long> {
	
}
