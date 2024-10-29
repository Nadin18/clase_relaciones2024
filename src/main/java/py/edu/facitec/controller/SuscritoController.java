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

import py.edu.facitec.model.Suscrito;
import py.edu.facitec.repository.SuscritoRepository;
import org.springframework.web.bind.annotation.RequestParam;


//Aplicar la Arquitectura rest
//Representational State Transfer
//Solicitudes sin alm. de estado
@RestController
@RequestMapping("/api")
//api afecta a todos los metodos de la clase
public class SuscritoController {
	@Autowired
	private SuscritoRepository suscritoRepository;
	@GetMapping("/suscritos")
	public ResponseEntity<List<Suscrito>> getSuscrito(){
		List<Suscrito> suscritos = new ArrayList<>();
		suscritos =suscritoRepository.findAll();
		return new ResponseEntity<List<Suscrito>>(suscritos, HttpStatus.OK);
	}
	
	//json>> java
	@PostMapping("/suscrito")
	public ResponseEntity<Suscrito> guardarSuscrito(@RequestBody Suscrito suscrito){
		suscritoRepository.save(suscrito);
		//devolver el nuevo objeto creado
		//id nuevo
		return new ResponseEntity<Suscrito>(suscrito, HttpStatus.OK);
	}
	//api/suscrito/{codigo}
	//buscar un suscrito por codigo
	@GetMapping("/suscrito/{codigo}")
	public ResponseEntity<Suscrito>getOneSuscrito(@PathVariable long codigo){
		Optional<Suscrito> suscrito =suscritoRepository.findById(codigo);
		//comparar si se encontro
		if(suscrito.isPresent()) {
			return new ResponseEntity<Suscrito>(suscrito.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
		//api/suscrito/id
		//eliminar un suscrito por codigo Delete
		@DeleteMapping("/suscrito/{codigo}")
		public ResponseEntity<Suscrito> eliminarOneSuscrito(@PathVariable long codigo){
			Optional<Suscrito>suscrito =suscritoRepository.findById(codigo);
			//comparar si se encontro
			if(suscrito.isPresent()) {
				suscritoRepository.deleteById(codigo);
				return new ResponseEntity<Suscrito>(suscrito.get(), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		
	}
}
