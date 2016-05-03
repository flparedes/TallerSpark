package es.franl2p.daos;

import java.util.List;

import es.franl2p.model.Car;

public class CarDao extends BasicDao {
	
	/**
	 * Creates the given car
	 * @param car
	 * @return
	 */
	public boolean createCar(Car car) {
		boolean exito = true;
		
		this.initTransaction();
		session.save(car);
		this.endTransaction();
		
		return exito;
	}
	
	/**
	 * Gets all the cars.
	 * @return 
	 */
	public List<Car> findAll() {
		this.initTransaction();
		List<Car> cars = session.createQuery("from Car").list();
		this.endTransaction();
		
		return cars;
	}
}
