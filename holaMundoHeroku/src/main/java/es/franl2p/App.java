package es.franl2p;

import static spark.Spark.*;

import es.franl2p.controller.CarsController;
import es.franl2p.controller.HolaMundoController;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String[] args) {
		// Configurate the Heroku App
		configureHerokuApp();
		
		// Creates the controllers
		new HolaMundoController();
		new CarsController();
	}

    static void configureHerokuApp() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        
        // If there's a PORT assigned by Heroku we set it.
        if (processBuilder.environment().get("PORT") != null) {
            port(Integer.parseInt(processBuilder.environment().get("PORT")));
        }
    }
    
}
