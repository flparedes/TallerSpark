package es.franl2p;

import static spark.Spark.staticFiles;

import es.franl2p.controller.CarsController;

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
		new CarsController();
	}
}
