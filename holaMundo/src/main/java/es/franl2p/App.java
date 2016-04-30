package es.franl2p;

import static spark.Spark.*;

public class App {
	public static void main( String[] args ) {
//		get("/hola", (req, res) -> "¡Hola Mundo!");
		
		get("/hola/:nombre", (req, res) -> {
			String hola = "¡Hola " + req.params(":nombre") + "!";
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
			
			return respuesta;
		});
    }
}
