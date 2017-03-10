package es.franl2p.controller;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.franl2p.model.Document;
import es.franl2p.services.DocumentService;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class MainController {
	private final static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	private DocumentService documentService = new DocumentService(); 

	public MainController() {
		// Init the routes
		
		/**
		 * Application root route
		 */
		get("/", (req, res) -> {
			Map<String, Object> attributes = new HashMap<String, Object>();
			
	        // attributes.put("textoCabecera", "MyMega");
			List <Document> docs = documentService.findAll();
			attributes.put("documents", docs);
	
	        return new ModelAndView(attributes, "main.ftl");
	    }, new FreeMarkerEngine());
	}
}
