package py.edu.facitec.controller;

import java.security.PublicKey; 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.model.Comentario;
import py.edu.facitec.repository.ComentarioRepository;
import org.springframework.web.bind.annotation.RequestParam;


//Aplicar la Arquitectura rest
//Representational State Transfer
//Solicitudes sin alm. de estado
@RestController
@RequestMapping("/api")
//api afecta a todos los metodos de la clase
public class ComentarioController {
	@Autowired
	private ComentarioRepository comentarioRepository;
	@GetMapping("/comentarios")
	public ResponseEntity<List<Comentario>> getComentario(){
		List<Comentario> comentarios = new ArrayList<>();
		comentarios =comentarioRepository.findAll();
		return new ResponseEntity<List<Comentario>>(comentarios, HttpStatus.OK);
	}
	
	//json>> java
	@PostMapping("/comentario")
	public ResponseEntity<Comentario> guardarComentario(@RequestBody Comentario comentario){
		comentarioRepository.save(comentario);
		//devolver el nuevo objeto creado
		//id nuevo
		return new ResponseEntity<Comentario>(comentario, HttpStatus.OK);
	}
	//api/comentario/{codigo}
	//buscar un comentario por codigo
	@GetMapping("/comentario/{codigo}")
	public ResponseEntity<Comentario>getOneComentario(@PathVariable long codigo){
		Optional<Comentario> comentario =comentarioRepository.findById(codigo);
		//comparar si se encontro
		if(comentario.isPresent()) {
			return new ResponseEntity<Comentario>(comentario.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
		//api/comentario/id
		//eliminar un comentario por codigo Delete
		@DeleteMapping("/comentario/{codigo}")
		public ResponseEntity<Comentario> eliminarOneComentario(@PathVariable long codigo){
			Optional<Comentario>comentario =comentarioRepository.findById(codigo);
			//comparar si se encontro
			if(comentario.isPresent()) {
				comentarioRepository.deleteById(codigo);
				return new ResponseEntity<Comentario>(comentario.get(), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		
	}
}
