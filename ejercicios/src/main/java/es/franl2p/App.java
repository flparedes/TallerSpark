package es.franl2p;

import static spark.Spark.get;
import static spark.Spark.notFound;

import es.franl2p.controller.DiceController;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		get("/", (req, res) -> "App running");

		// Undefined routes
		notFound((req, res) -> {
			return "<html><body><h1>Error 404</h1><p>La página " + req.url()
					+ " solicitada no existe</p></body></html>";
		});
		
		// Init controllers
		new DiceController();
	}
}
