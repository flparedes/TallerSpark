package es.franl2p.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import es.franl2p.daos.CarDao;
import es.franl2p.daos.ManufacturerDao;
import es.franl2p.model.Car;
import es.franl2p.model.Manufacturer;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class CarsController {
	
	private CarDao carDao = new CarDao();
	private ManufacturerDao manufacturerDao = new ManufacturerDao();
	
	public CarsController() {
		// Init the routes
		/**
		 * Ruta del formulario de pruebas
		 */
		get("/form", (req, res) -> {
			Map<String, Object> attributes = new HashMap<String, Object>();

	        attributes.put("coches", carDao.findAll());
	
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
		
		/**
		 * Ruta del formulario para crear/editar coches
		 */
		get("/car", (req, res) -> {
			Map<String, Object> attributes = new HashMap<String, Object>();
			
			attributes.put("fabricantes", manufacturerDao.findAll());

	        return new ModelAndView(attributes, "car.ftl");
	    }, new FreeMarkerEngine());
		
		/**
		 * Ruta del POST para el formulario de pruebas
		 */
		post("/car", (req, res) -> {
			Map<String, Object> attributes = new HashMap<String, Object>();
			
			// Se recuperan los parametros
			String nombre = req.queryParams("nombre");
			String fabricante = req.queryParams("fabricante");
			
			Manufacturer manufacturer = manufacturerDao.findById(new Integer(fabricante));
			
			carDao.createCar(new Car(null, nombre, manufacturer));
			
			// Se cargan en el modelo
			attributes.put("result", "Coche creado.");
			attributes.put("nombre", nombre);
			attributes.put("fabricantes", manufacturerDao.findAll());
	
	        return new ModelAndView(attributes, "car.ftl");
	    }, new FreeMarkerEngine());
	}
}
