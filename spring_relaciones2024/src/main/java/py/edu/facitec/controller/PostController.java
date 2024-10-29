package py.edu.facitec.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import py.edu.facitec.model.Post;
import py.edu.facitec.repository.PostRepository;
import org.springframework.web.bind.annotation.RequestParam;

// Aplicarnla arquitetura Rest
//representational State tranfer
//Solicitudes sin almacenamiento de estado.
@RestController
@RequestMapping("/api")
// api afecta a todos los metodos de la clase--------------------------------------------------
public class PostController {

	// AutoWired de Post Repository
	@Autowired
	private PostRepository postRepository;

	//@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
	//private LocalDate fecha;
	// api

	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getPosts() {

		List<Post> posts = new ArrayList<>();
		// buscar todos
		posts = postRepository.findAll();

		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	//--------------------------------------------

	// Post se utiliza para crear un elemento
	@PostMapping("/post")
	public ResponseEntity<Post>
			// Json--> JAVA
			guardarPost(@RequestBody Post post) {

		postRepository.save(post);
		// devolver el objeto creado
		// id nuevo
		return new ResponseEntity<Post>(post, HttpStatus.OK);

	}// -------------------------------------------------------------

	// api/post/{id}
	// buscar un post por id
	@GetMapping("/post/{codigo}")
	public ResponseEntity<Post>
			// Recibir el parametro
			getOnePost(@PathVariable Long codigo) {

		Optional<Post> post = postRepository.findById(codigo);

		// comparar si se encontro
		if (post.isPresent()) {
			return new ResponseEntity<Post>(post.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
		}
	}
	// ------------------------------------------------------------
	// api/post/codigo
	// Eliminado un post por codigo Delete

	@DeleteMapping("/post/{codigo}")
	public ResponseEntity<Post> eliminarOnePost(@PathVariable Long codigo) {

		Optional<Post> post = postRepository.findById(codigo);

		// comparar si se encontro
		if (post.isPresent()) {
			// elimina un post
			postRepository.deleteById(codigo);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);

		}//-----------------------------------------------------
	}
}
