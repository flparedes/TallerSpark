package es.franl2p.daos;

import java.util.List;

import es.franl2p.model.Manufacturer;

public class ManufacturerDao extends BasicDao<Manufacturer> {
	
	public ManufacturerDao() {
		this.configureEntityName();
	}
	
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
	 * Gets all the Manufacturers.
	 * @return 
	 */
	public List<Manufacturer> findAll() {
		this.initTransaction();
		List<Manufacturer> manufacturers = session.createQuery("from Manufacturer").list();
		this.endTransaction();
		
		return manufacturers;
	}

	@Override
	protected void configureEntityName() {
		this.entityName = Manufacturer.class.getName();
	}
}
