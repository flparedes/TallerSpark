package es.franl2p.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import es.franl2p.util.CarLoader;
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
		
		/**
		 * Ruta del formulario de pruebas
		 */
		get("/form", (req, res) -> {
			Map<String, Object> attributes = new HashMap<String, Object>();

	        attributes.put("coches", CarLoader.loadCars());
	
	        return new ModelAndView(attributes, "formulario.ftl");
	    }, new FreeMarkerEngine());
		
		/**
		 * Ruta del POST para el formulario de pruebas
		 */
		post("/form", (req, res) -> {
			Map<String, Object> attributes = new HashMap<String, Object>();
			
			// Se recuperan los parametros
			String nombre = req.queryParams("nombre");
			String texto = req.queryParams("texto");
			String[] coches = req.queryParamsValues("coches");
			String listaCoches = null;
			if (coches != null) {
				listaCoches = "";
				for (String coche : coches) {
					listaCoches += coche + " ";
				}
			}
			
			// Se cargan en el modelo
			attributes.put("nombre", nombre);
			attributes.put("texto", texto);
			attributes.put("coches", listaCoches);
	
	        return new ModelAndView(attributes, "resultado.ftl");
	    }, new FreeMarkerEngine());
	}
}
