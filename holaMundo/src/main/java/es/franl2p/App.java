package es.franl2p;

import static spark.Spark.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.route.RouteOverview;

public class App {
	private final static Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main( String[] args ) {
		RouteOverview.enableRouteOverview();

		get("/", (req, res) -> "App running");
		
		get("/hola", (req, res) -> "¡Hola Mundo!");
		
		get("/hola/:nombre", (req, res) -> {
			String hola = "¡Hola " + req.params(":nombre") + "!";
			logger.info("hola: " + hola);
			return hola;
		});
		
		get("/hola", (req, res) -> {
			String respuesta = "¡Hola Mundo!";
			
			String nombre = req.queryParams("nombre");
			String redirect = req.queryParams("redirect");
			
			if (nombre != null ) {
				respuesta = "¡Hola " + nombre + "!";
				if (redirect != null && "Y".equals(redirect)) {
					res.redirect("/hola/" + nombre);
				}
			}
			logger.info("respuesta: " + respuesta);
			
			return respuesta;
		});
		
		get("/stop", (req, res) -> {
			logger.info("Stopping App...");
			stop();
			return "Stopping App...";
		});
		
		// Rutas no definidas
		notFound((req, res) -> {
		    return "<html><body><h1>Error 404</h1><p>La página " + req.url() + " solicitada no existe</p></body></html>";
		});
    }
}
