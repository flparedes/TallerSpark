package es.franl2p.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.franl2p.daos.CarDao;
import es.franl2p.daos.ManufacturerDao;
import es.franl2p.model.Car;
import es.franl2p.model.Manufacturer;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class CarsController {
	private final static Logger logger = LoggerFactory.getLogger(CarsController.class);
	
	private CarDao carDao = new CarDao();
	private ManufacturerDao manufacturerDao = new ManufacturerDao();
	
	public CarsController() {
		// Init the routes
		/**
		 * Application root route
		 */
		get("/", (req, res) -> {
			Map<String, Object> attributes = new HashMap<String, Object>();

	        attributes.put("textoCabecera", "Lista de coches");
	        attributes.put("coches", carDao.findAll());
	
	        return new ModelAndView(attributes, "list.ftl");
	    }, new FreeMarkerEngine());
		 
		/**
		 * Ruta del formulario de fabricante
		 */
		get("/manufacturer/new", (req, res) -> {
			Map<String, Object> attributes = new HashMap<String, Object>();

	        attributes.put("textoCabecera", "Nuevo fabricante");
	        attributes.put("manufacturers", manufacturerDao.findAll());
	
	        return new ModelAndView(attributes, "formularioManufacturer.ftl");
	    }, new FreeMarkerEngine());
		
		/**
		 * Ruta del POST para el formulario de fabricante
		 */
		post("/manufacturer/new", (req, res) -> {
			String result = "resultadoManufacturer.ftl";
			Map<String, Object> attributes = new HashMap<String, Object>();
			
			// Se recuperan los parametros
			String nombre = req.queryParams("nombre");
			String pais = req.queryParams("pais");

			logger.info("nombre: " + nombre);
			logger.info("pais: " + pais);
			
			Manufacturer manufacturer = new Manufacturer();
			manufacturer.setName(nombre);
			manufacturer.setCountry(pais);
			
			if (manufacturer.validate()) {
		        attributes.put("textoCabecera", "Fabricante creado");
		        attributes.put("result", "Fabricante creado correctamente.");
				manufacturerDao.createManufacturer(manufacturer);
			} else {
		        attributes.put("textoCabecera", "Nuevo coche");
		        attributes.put("errores", manufacturer.getErrors());
		        result = "formularioManufacturer.ftl";
			}

			logger.info("result: " + result);
			
			// Se cargan en el modelo los parámetros
			attributes.put("nombre", nombre);
			attributes.put("pais", pais);
	
	        return new ModelAndView(attributes, result);
	    }, new FreeMarkerEngine());
		
		/**
		 * Ruta del formulario para crear/editar coches
		 */
		get("/car/new", (req, res) -> {
			Map<String, Object> attributes = new HashMap<String, Object>();

	        attributes.put("textoCabecera", "Nuevo coche");
	        attributes.put("manufacturers", manufacturerDao.findAll());

	        return new ModelAndView(attributes, "formularioCar.ftl");
	    }, new FreeMarkerEngine());
		
		/**
		 * Ruta del POST para el formulario de pruebas
		 */
		post("/car/new", (req, res) -> {
			String result = "resultadoCar.ftl";
			Map<String, Object> attributes = new HashMap<String, Object>();
			
			// Se recuperan los parametros
			String nombre = req.queryParams("nombre");
			String fabricante = req.queryParams("fabricante");

			logger.info("nombre: " + nombre);
			logger.info("fabricante: " + fabricante);
			
			Manufacturer manufacturer = manufacturerDao.findById(new Integer(fabricante));
			logger.info("manufacturer: " + manufacturer);
			Car coche = new Car(null, nombre, manufacturer);
			
			if (manufacturer.validate()) {
		        attributes.put("textoCabecera", "Coche creado");
		        attributes.put("result", "Coche creado correctamente.");
				carDao.createCar(coche);
			} else {
		        attributes.put("textoCabecera", "Nuevo coche");
		        attributes.put("errores", manufacturer.getErrors());
		        result = "formularioManufacturer.ftl";
			}

			logger.info("result: " + result);
			
			// Se cargan en el modelo
			attributes.put("nombre", nombre);
			attributes.put("fabricante", manufacturer);
	
	        return new ModelAndView(attributes, result);
	    }, new FreeMarkerEngine());
		
		/**
		 * Filtro que añade 
		 */
		after((request, response) -> {
	        response.header("foo", "set by after filter");
	    });
	}
}
