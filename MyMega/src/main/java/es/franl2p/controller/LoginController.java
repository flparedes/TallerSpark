package es.franl2p.controller;

import static spark.Spark.*;
import spark.*;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.franl2p.services.LoginService;
import es.franl2p.utils.Constants;
import spark.template.freemarker.FreeMarkerEngine;

public class LoginController {
	private final static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	private LoginService loginService = new LoginService();

	public LoginController() {
		
		/**
		 * Rutas para manufacturer
		 */
		path("/login", () -> {
			/**
			 * Show login page
			 */
			get("", (req, res) -> {
				Map<String, Object> attributes = new HashMap<String, Object>();
	
		        attributes.put("textoCabecera", "MyMega");
		
		        return new ModelAndView(attributes, "login/login.ftl");
		    }, new FreeMarkerEngine());

			/**
			 * Post login credentials
			 */
			post("", (req, res) -> {
				String view = "main.ftl";
				Map<String, Object> attributes = new HashMap<String, Object>();
	
		        attributes.put("textoCabecera", "MyMega");
		        
		        String user = req.queryParams("user");
				String pass = req.queryParams("pass");
				
				if (loginService.loginUser(user, pass, req.session())) {
					// User logged in
					String path = req.session().attribute(Constants.PATH_AFTER_LOGIN);
					if (path != null && !path.isEmpty()) {
						res.redirect(path);
					}
				} else {
					attributes.put("user", user);
					attributes.put("error", "No se ha podido auntenticar el usuario. Compruebe que los datos introducidos son correctos.");
					view = "login/login.ftl";
				}
		
		        return new ModelAndView(attributes, view);
		    }, new FreeMarkerEngine());
		});
		
		/**
		 * Rutas para manufacturer
		 */
		path("/logout", () -> {
			/**
			 * Application root route
			 */
			get("/", (req, res) -> {
				Map<String, Object> attributes = new HashMap<String, Object>();
	
		        attributes.put("textoCabecera", "MyMega");
		
		        return new ModelAndView(attributes, "main.ftl");
		    }, new FreeMarkerEngine());
		});
	}
}
