package py.edu.facitec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Anotacion@= Provee informacion adicional a una clase,
//atributo o metodo

// Un elemento que atienda peticiones y envie respuestas
// necesitamos indicar @controller

// Peticiones----> request  lo que biene del cliente
// Respuestas----> response lo que sale del servidor

@Controller
public class HomeController {
	
	// Pueden existir varios metodos para
	//atender determinadas peticiones /..
	
	@GetMapping ("/home")  //Request
	public String home() {
		
		System.out.println("Llegue hasta el controlador");
		// static/   arcrivo/    index          .html
		//defecto   ap.proper   controller    ap.proper
		
		return "index";  // Response
		
	}
	
	
	

}
