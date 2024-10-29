package py.edu.facitec.model;

import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
//aplicacion de herencia para la persistencia
@MappedSuperclass
public abstract class General {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	public General() {
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "General [id=" + id + "]";
	}
	
}
