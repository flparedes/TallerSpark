package es.franl2p.controller;

import static spark.Spark.*;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiceController {
	private final static Logger logger = LoggerFactory.getLogger(DiceController.class);
	
	public DiceController() {
		
		// Ruta para lanzar dados y ver el resultado de la tirada.
		get("/roll/:num/d/:caras", (req, res) -> {
			Integer numDados = convert2Number(req.params(":num"));
			logger.info("numDados: " + numDados);
			Integer numCaras = convert2Number(req.params(":caras"));
			logger.info("numCaras: " + numCaras);
			
			String result = "";
			int counter = 0;
			if (numDados != null && numCaras != null) {
				Random random = new Random(new Date().getTime());
				for (int i=0; i < numDados; i++) {
					int resDado = random.nextInt(numCaras) + 1;
					result += (result.isEmpty() ? resDado : " + " + resDado);
					counter += resDado;
				}
				
				result += " = " + counter;
			} else {
				res.status(400);
				result = "Parámetros incorrectos. Sólo se admiten números mayores que 0. Por ejemplo: /2/d/20";
			}

			logger.info("result: " + result);
			return result;
		});
	}
	
	/**
	 * Takes a String and converts it to a positive greater than 0 Integer number.
	 * @param num String with the number to convert.
	 * @return Integer with the converted number or null if it cannot be converted.
	 */
	private Integer convert2Number (String num) {
		Integer number = null;
		
		try {
			number = new Integer(num);
			if (number <= 0) {
				number = null;
				logger.error("The number " + num + " isn't greater than 0.");
			}
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
		}
		
		return number;
	}
}
