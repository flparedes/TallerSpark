package es.franl2p.controller;

import static spark.Spark.*;
import spark.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.franl2p.model.Document;
import es.franl2p.model.User;
import es.franl2p.services.DocumentService;
import es.franl2p.services.LoginService;
import es.franl2p.utils.Constants;
import es.franl2p.utils.Converters;
import es.franl2p.utils.DocumentUtils;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import spark.utils.IOUtils;

public class MainController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	private DocumentService documentService = new DocumentService();
	private LoginService loginService = new LoginService();
	
	public MainController() {
		super();
		
		/**
		 * Application root route
		 */
		get(Constants.MAIN_ROUTE, (req, res) -> {
			Map<String, Object> attributes = new HashMap<String, Object>();
			
	        // attributes.put("textoCabecera", "MyMega");
			List <Document> docs = documentService.findAll();
			attributes.put("documents", docs);
			
			attributes = this.addSessionAttributes(req, attributes);
	
	        return new ModelAndView(attributes, "main.ftl");
	    }, new FreeMarkerEngine());
		
		/**
		 * Download document route
		 */
		get(Constants.DOWNLOAD_ROUTE + "/:docId", (req, res) -> {
			Integer docId = Converters.convert2Number(req.params(":docId"));
			logger.info("docId: " + docId);
			
			Document doc = documentService.findById(docId);
			logger.info("doc: " + doc);
			
			return this.generarRespuesta(res, doc);
	    });
		
		/**
		 * New doc form
		 */
		get(Constants.NEW_DOC_ROUTE, (req, res) -> {
			Map<String, Object> attributes = new HashMap<String, Object>();
			
	        // attributes.put("textoCabecera", "MyMega");
			List <Document> docs = documentService.findAll();
			attributes.put("documents", docs);
			
			attributes = this.addSessionAttributes(req, attributes);
	
	        return new ModelAndView(attributes, "new.ftl");
	    }, new FreeMarkerEngine());
		
		/**
		 * New doc post
		 */
		post(Constants.NEW_DOC_ROUTE, (request, response) -> {
		    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
		    Part part = request.raw().getPart("document");
		    
		    String view = "new.ftl";
        	Map<String, Object> attributes = new HashMap<String, Object>();
		    
		    try (InputStream is = part.getInputStream()) {
			    String type = DocumentUtils.getType(part);
		    	String fileName = DocumentUtils.getFileName(part);
		        logger.info("Document " + fileName + " received. Type: " + type);
		        
		        byte[] data = IOUtils.toByteArray(is);
		    	User user = loginService.getLoggedInUser(request.session());
		    	Document documento = new Document(null, fileName, type, data, user);
		        
		    	if (documento.validate()) {
			        if (!documentService.insert(documento)) {
			        	attributes.put("error", Constants.ERROR_UPLOADING);
			        	view = "error.ftl";
			        } else {
					    response.redirect(Constants.MAIN_ROUTE);
					    return null;
			        }
		    	} else {
			        attributes.put("errors", documento.getErrors());
		    	}
		    } catch (Exception e) {
				e.printStackTrace();
			}

	        return new ModelAndView(attributes, view);
		}, new FreeMarkerEngine());
	}
	

	/**
	 * Se genera la respuesta en función del documento y del código de estado dado.
	 * @param res Respuesta de Spark donde cargar el resultado.
	 * @param docFirmado Documento firmado a cargar en la respuesta.
	 * @param statusCode Código de estado de la operación ejecutada.
	 * @return Salida de la respuesta.
	 */
	private String generarRespuesta(Response res, Document doc) {
		int statusCode = 404;
		String result = null;
		
		// Si todo ha ido bien se intentar cargar el documento.
		if (doc != null && doc.getData() != null) {
			try {
				logger.info("Nombre Documento: " + doc.getName());
				// Se cargan los datos del documento.
				res.type(doc.getType());
				res.header("Content-Disposition", "attachment; filename=" + doc.getName());
				
				// A continuación cargamos el contenido por si se produce una excepción
				res.raw().getOutputStream().write(doc.getData());
				res.raw().getOutputStream().flush();
				res.raw().getOutputStream().close();
				statusCode = 200;
			} catch (IOException e) {
				statusCode = 500;
				logger.error("Excepción de E/S", e);
			}
		} else {
			// Si no hay documento o no tiene contenido se cambia el statusCode a 500.
			statusCode = 500;
		}
		
		// Se carga el statusCode en la respuesta.
		res.status(statusCode);
		
		switch (statusCode) {
		case 400:
			res.body(Constants.ERROR_400);
			result = Constants.ERROR_400;
			break;
		case 404:
			res.body(Constants.ERROR_404);
			result = Constants.ERROR_404;
			break;
		case 500:
			res.body(Constants.ERROR_500);
			result = Constants.ERROR_500;
			break;
		default:
			break;
		}
		
		return result;
	}
}
