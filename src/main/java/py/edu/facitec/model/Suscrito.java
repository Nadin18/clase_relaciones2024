package py.edu.facitec.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

//Crear la tabla en la base de datos.
@Entity
public class Suscrito {

	
	@Id   // ID
	@GeneratedValue  //GENERAR AUTOMATICO
	private Long codigo;
	private String nombre;
	private String correo;
	@OneToMany(mappedBy = "suscrito")
	private List<Comentario> comentario;
	public Suscrito() {
		// TODO Auto-generated constructor stub
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public List<Comentario> getComentario() {
		return comentario;
	}
	public void setComentario(List<Comentario> comentario) {
		this.comentario = comentario;
	}
	@Override
	public String toString() {
		return "Suscrito [codigo=" + codigo + ", nombre=" + nombre + ", correo=" + correo + ", comentario=" + comentario
				+ "]";
	}
	
}
