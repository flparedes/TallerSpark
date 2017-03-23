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
		    	loginService.checkLoggedIn(request, response);
		    }
		});
	}
	
	protected Map<String, Object> addSessionAttributes(Request request, Map<String, Object> attributes) {
		attributes.put("user", request.session().attribute(Constants.USER_SESSION));
		
		return attributes;
	}
}
