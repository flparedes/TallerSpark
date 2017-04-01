package es.franl2p.controller;

import static spark.Spark.*;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.franl2p.services.LoginService;
import es.franl2p.utils.Constants;
import spark.Request;

public abstract class BaseController {
	private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

	private LoginService loginService = new LoginService();
	
	public BaseController() {
		before((request, response) -> {
		    // Check if route is protected
			String route = request.pathInfo();
//			String uri = request.uri();
			
		    if (Constants.PROTECTED_ROUTES.contains(route)) {
		    	logger.info("Try to access to the protected route: " + route);
		    	if (!loginService.isLoggedIn(request, response)) {
		        	// Store the requested path and redirect to login.
		            request.session().attribute(Constants.PATH_AFTER_LOGIN, request.pathInfo());
		            response.redirect(Constants.LOGIN_ROUTE);
		            
		            // Halt the request
		        	halt();
		        }
		    }
		});
	}
	
	protected Map<String, Object> addSessionAttributes(Request request, Map<String, Object> attributes) {
		attributes.put("user", request.session().attribute(Constants.USER_SESSION));
		
		return attributes;
	}
}
