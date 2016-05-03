package es.franl2p.daos;

import java.util.List;

import es.franl2p.model.Manufacturer;

public class ManufacturerDao extends BasicDao {
	
	/**
	 * Creates the given Manufacturer
	 * @param manufacturer
	 * @return
	 */
	public boolean createManufacturer(Manufacturer manufacturer) {
		boolean exito = true;
		
		this.initTransaction();
		session.save(manufacturer);
		this.endTransaction();
		
		return exito;
	}
	
	/**
	 * Gets the manufacturer by it's ID.
	 * @param id Manufacturer ID.
	 * @return
	 */
	public Manufacturer findById(Integer id) {
		Manufacturer manufacturer = null;
		this.initTransaction();
		manufacturer = session.load(Manufacturer.class, id);
		this.endTransaction();
		
		return manufacturer;
	}
	
	/**
	 * Gets all the Manufacturers.
	 * @return 
	 */
	public List<Manufacturer> findAll() {
		this.initTransaction();
		List<Manufacturer> manufacturers = session.createQuery("from Manufacturer").list();
		this.endTransaction();
		
		return manufacturers;
	}
}
