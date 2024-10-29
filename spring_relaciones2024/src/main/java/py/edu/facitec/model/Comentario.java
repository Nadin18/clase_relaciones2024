package py.edu.facitec.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Comentario  extends General{
	
	private String texto;
	private Integer estrellas;
	
	
	
	@JsonBackReference(value = "relacion1")//cuando la lista es mas importante"""examen
	@ManyToOne								// relacion muchos a uno
	private Post post;
	
	
	
	@ManyToOne
	private Suscrito suscrito;

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * @return the estrellas
	 */
	public Integer getEstrellas() {
		return estrellas;
	}

	/**
	 * @param estrellas the estrellas to set
	 */
	public void setEstrellas(Integer estrellas) {
		this.estrellas = estrellas;
	}

	/**
	 * @return the post
	 */
	public Post getPost() {
		return post;
	}

	/**
	 * @param post the post to set
	 */
	public void setPost(Post post) {
		this.post = post;
	}

	/**
	 * @return the suscrito
	 */
	public Suscrito getSuscrito() {
		return suscrito;
	}

	/**
	 * @param suscrito the suscrito to set
	 */
	public void setSuscrito(Suscrito suscrito) {
		this.suscrito = suscrito;
	}

	@Override
	public String toString() {
		return "Comentario [texto=" + texto + ", estrellas=" + estrellas + ", post=" + post + ", suscrito=" + suscrito
				+ "]";
	}

	

	
}
