package es.franl2p.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.franl2p.utils.Constants;
import spark.Request;

public abstract class BaseController {
	private final static Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	protected Map<String, Object> addSessionAttributes(Request request, Map<String, Object> attributes) {
		attributes.put("user", request.session().attribute(Constants.USER_SESSION));
		
		return attributes;
	}
}
