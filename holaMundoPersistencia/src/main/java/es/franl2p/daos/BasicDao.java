package es.franl2p.daos;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public abstract class BasicDao<T> {
	
	// Hibernate factories
	private static final SessionFactory sessionFactory = configureSessionFactory();
	
	protected Session session;
	protected String entityName;

	private static SessionFactory configureSessionFactory() throws HibernateException {  
//        Configuration configuration = new Configuration();  
//        configuration.configure();  
         
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
        		.configure() // configures settings from hibernate.cfg.xml
    			.build();          
        return new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
    }

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
	
	protected void initTransaction() {
		session = sessionFactory.openSession();
		session.beginTransaction();
	}
	
	protected void endTransaction() {
		session.getTransaction().commit();
        session.close();
	}
	
	/**
	 * Gets the entity by it's ID.
	 * @param id Entity ID.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T findById(Integer id) {
		T entity = null;
		this.initTransaction();
		entity = (T) session.get(entityName, id);
		this.endTransaction();
		
		return entity;
	}
	
	/**
	 * Deletes a car with the given id
	 * @param id Car identifier.
	 * @return true if deleted or false if not.
	 */
	public boolean delete(Integer id) {
		boolean exito = false;
		
		T entity = findById(id);
		
		if (entity != null) {
			this.initTransaction();
			session.delete(entity);
			this.endTransaction();
			exito = true;
		}
		
		return exito;
	}
	
	/**
	 * Sets the entityName.
	 */
	protected abstract void configureEntityName();
}
