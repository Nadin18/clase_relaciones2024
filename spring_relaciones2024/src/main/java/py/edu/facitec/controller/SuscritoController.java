package py.edu.facitec.controller;

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

import py.edu.facitec.model.Suscrito;
import py.edu.facitec.repository.SuscritoRepository;
import org.springframework.web.bind.annotation.RequestParam;

// Aplicarnla arquitetura Rest
//representational State tranfer
//Solicitudes sin almacenamiento de estado.
@RestController
@RequestMapping("/api")
// api afecta a todos los metodos de la clase--------------------------------------------------
public class SuscritoController {

	// AutoWired de Suscrito Repository
	@Autowired
	private SuscritoRepository suscritoRepository;

	// api

	@GetMapping("/suscritos")
	public ResponseEntity<List<Suscrito>> getSuscritos() {

		List<Suscrito> suscritos = new ArrayList<>();
		// buscar todos
		suscritos = suscritoRepository.findAll();

		return new ResponseEntity<List<Suscrito>>(suscritos, HttpStatus.OK);
	}

	// Post se utiliza para crear un elemento
	@PostMapping("/suscrito")
	public ResponseEntity<Suscrito>
			// Json--> JAVA
			guardarSuscrito(@RequestBody Suscrito suscrito) {

		suscritoRepository.save(suscrito);
		// devolver el objeto creado
		// id nuevo
		return new ResponseEntity<Suscrito>(suscrito, HttpStatus.OK);

	}// -------------------------------------------------------------

	// api/suscrito/{id}
	// buscar un suscrito por id
	@GetMapping("/suscrito/{codigo}")
	public ResponseEntity<Suscrito>
			// Recibir el parametro
			getOneSuscrito(@PathVariable Long codigo) {

		Optional<Suscrito> suscrito = suscritoRepository.findById(codigo);

		// comparar si se encontro
		if (suscrito.isPresent()) {
			return new ResponseEntity<Suscrito>(suscrito.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Suscrito>(HttpStatus.NOT_FOUND);
		}
	}
	// ------------------------------------------------------------
	// api/suscrito/codigo
	// Eliminado un suscrito por codigo Delete

	@DeleteMapping("/suscrito/{codigo}")
	public ResponseEntity<Suscrito> eliminarOneSuscrito(@PathVariable Long codigo) {

		Optional<Suscrito> suscrito = suscritoRepository.findById(codigo);

		// comparar si se encontro
		if (suscrito.isPresent()) {
			// elimina un suscrito
			suscritoRepository.deleteById(codigo);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Suscrito>(HttpStatus.NOT_FOUND);

		}//-----------------------------------------------------
	}
}
