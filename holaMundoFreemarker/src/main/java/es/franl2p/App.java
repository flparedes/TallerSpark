package es.franl2p;

import es.franl2p.controller.HolaMundoController;
import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// Static files
		staticFiles.location("/public");
		staticFiles.expireTime(600L);

		// Creates the controllers
		new HolaMundoController();
	}
}
