package es.franl2p.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.franl2p.util.CarLoader;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class HolaMundoController {
	private final static Logger logger = LoggerFactory.getLogger(HolaMundoController.class);
	
	private static final String TEXTO = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.";
	
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
		 * Hola Mundo con layout
		 */
		get("/holaLayout", (req, res) -> {
			Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put("saludo", "¡Hola Mundo!");
	        attributes.put("textoCabecera", "Texto de cabecera");
	        attributes.put("texto", TEXTO);
	
	        // Las plantillas se ubican en el directorio
	        // src/main/resources/spark/template/freemarker
	        return new ModelAndView(attributes, "holaLayout.ftl");
	    }, new FreeMarkerEngine());
		
		/**
		 * Ruta del formulario de pruebas
		 */
		get("/form", (req, res) -> {
			Map<String, Object> attributes = new HashMap<String, Object>();

	        attributes.put("coches", CarLoader.loadCars());
	        attributes.put("textoCabecera", "Ejemplo de formulario");
	
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
			
			List <String> errores = validarDatos(nombre, texto, coches);
			
			// Se cargan en el modelo
			attributes.put("nombre", nombre);
			attributes.put("texto", texto);
	        attributes.put("coches", CarLoader.loadCars());
			attributes.put("cochesSeleccionados", listaCoches);
			attributes.put("errores", errores);
	        attributes.put("textoCabecera", "Resultado del submit");
	        
	        String returnPage = "resultado.ftl";
	        if (errores != null) {
	        	returnPage = "formulario.ftl";
	        }
	
	        return new ModelAndView(attributes, returnPage);
	    }, new FreeMarkerEngine());
	}
	
	private List<String> validarDatos(String nombre, String texto, String[] coches) {
		boolean hayErrores = false;
		List<String> lista = new LinkedList<String>();
		
		if (nombre == null || "".equals(nombre.trim())) {
			lista.add("El nombre es obligatorio.");
			hayErrores = true;
		}
		if (texto == null || "".equals(texto.trim())) {
			lista.add("El texto es obligatorio.");
			hayErrores = true;
		}
		if (coches == null || coches.length == 0) {
			lista.add("Selecciona al menos un coche.");
			hayErrores = true;
		}

		logger.info("errores: " + hayErrores);
		logger.info("lista: " + lista);
		
		if (!hayErrores) {
			lista = null;
		}
		
		return lista;
	}
}
