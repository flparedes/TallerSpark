package es.franl2p.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class HolaMundoController {
	
	public HolaMundoController() {
		// Init the routes
		
		/**
		 * Application root route
		 */
		get("/", (req, res) -> "¡Bienvenido a Hola mundo con Freemarker!");
		
		/**
		 * Hola Mundo route
		 */
		get("/hola", (req, res) -> {
			Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put("saludo", "¡Hola Mundo!");
	
	        // Las plantillas se ubican en el directorio
	        // src/main/resources/spark/template/freemarker
	        return new ModelAndView(attributes, "hola.ftl");
	    }, new FreeMarkerEngine());
	}
}
