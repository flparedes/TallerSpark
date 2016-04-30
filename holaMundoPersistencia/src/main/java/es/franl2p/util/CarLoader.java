package es.franl2p.util;

import java.util.LinkedList;
import java.util.List;

import es.franl2p.dto.Car;

public class CarLoader {
	
	/**
	 * Carga de los coches en una lista.
	 * @return Lista de coches.
	 */
	public static List<Car> loadCars() {
        List<Car> coches = new LinkedList<>();
        
        coches.add(new Car(1, "Mazda 3"));
        coches.add(new Car(2, "Toyota Land Cruiser"));
        coches.add(new Car(3, "Seat Leon"));
        
        return coches;
	}
}
