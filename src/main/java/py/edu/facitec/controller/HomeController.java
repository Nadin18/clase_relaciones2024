package py.edu.facitec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//este es una anotacion provee informacion adicional a una clase atributo o metodo
//un elemento que atienda peticiones y envie respuesas
//necesitamos indicar @controller
//peticiones---> request lo que viene del cliente
//Repuestas----> reponse lo que sale del servidor
@Controller
public class HomeController {
	//pueden exitir varios metodos
	//para atender determinadas peticiones /...
	@GetMapping("/home")//request
	public String home() {
		System.out.println("llegue al controlador");
		//static    /archivo  /index        .html
		//defecto  ap.proper  controller    ap.proper
		return "index";//response
	}
}
