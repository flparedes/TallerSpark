package es.franl2p;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import es.franl2p.controller.HolaMundoController;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		
		/**
		 * Hola Mundo route
		 */
//		get("/hola", (req, res) -> {
//			Map<String, Object> attributes = new HashMap<String, Object>();
//	        attributes.put("saludo", "¡Hola Mundo!");
//	
//	        // Las plantillas se ubican en el directorio
//	        // src/main/resources/spark/template/freemarker
//	        return new ModelAndView(attributes, "hola.ftl");
//	    }, new FreeMarkerEngine());

		// Creates the controllers
		new HolaMundoController();
	}
}
