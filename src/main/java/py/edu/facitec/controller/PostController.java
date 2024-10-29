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

import py.edu.facitec.model.Post;
import py.edu.facitec.repository.PostRepository;
import org.springframework.web.bind.annotation.RequestParam;


//Aplicar la Arquitectura rest
//Representational State Transfer
//Solicitudes sin alm. de estado
@RestController
@RequestMapping("/api")
//api afecta a todos los metodos de la clase
public class PostController {
	@Autowired
	private PostRepository postRepository;
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getPost(){
		List<Post> posts = new ArrayList<>();
		posts =postRepository.findAll();
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	//json>> java
	@PostMapping("/post")
	public ResponseEntity<Post> guardarPost(@RequestBody Post post){
		postRepository.save(post);
		//devolver el nuevo objeto creado
		//id nuevo
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	//api/post/{codigo}
	//buscar un post por codigo
	@GetMapping("/post/{codigo}")
	public ResponseEntity<Post>getOnePost(@PathVariable long codigo){
		Optional<Post> post =postRepository.findById(codigo);
		//comparar si se encontro
		if(post.isPresent()) {
			return new ResponseEntity<Post>(post.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
		//api/post/id
		//eliminar un post por codigo Delete
		@DeleteMapping("/post/{codigo}")
		public ResponseEntity<Post> eliminarOnePost(@PathVariable long codigo){
			Optional<Post>post =postRepository.findById(codigo);
			//comparar si se encontro
			if(post.isPresent()) {
				postRepository.deleteById(codigo);
				return new ResponseEntity<Post>(post.get(), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		
	}
}
