package es.franl2p;

import static spark.Spark.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.franl2p.controller.LoginController;
import es.franl2p.controller.MainController;

public class MyMega {
	private final static Logger logger = LoggerFactory.getLogger(MyMega.class);
	
	public static void main(String[] args) {
		// Static files
		staticFiles.location("/public");
		staticFiles.expireTime(600L);
		
		// Configurar la App para Heroku
		configureHerokuApp();
		
		// Creates the controllers
		new MainController();
		new LoginController();
	}

    static void configureHerokuApp() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        
        // If there's a PORT assigned by Heroku we set it.
        if (processBuilder.environment().get("PORT") != null) {
            port(Integer.parseInt(processBuilder.environment().get("PORT")));
        }
    }
}
