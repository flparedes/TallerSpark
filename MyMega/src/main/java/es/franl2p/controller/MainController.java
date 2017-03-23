package es.franl2p.controller;

import static spark.Spark.*;

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
		    	String fileName = getFileName(part);
		        logger.info("Document " + fileName + " received");
		        
		        byte[] data = IOUtils.toByteArray(is);
		    	User user = loginService.getLoggedInUser(request.session());
		    	Document documento = new Document(null, fileName, data, user);
		        
		    	if (documento.validate()) {
			        if (!documentService.insert(documento)) {
			        	attributes.put("error", "Error uploading file");
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
	
	private static String getFileName(Part part) {
		String docName = "document";
		String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
            	docName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            	docName = docName.replace("\\", "/");
            	docName = docName.substring(docName.lastIndexOf("/") + 1);
            }
        }
        return docName;
    }
}
