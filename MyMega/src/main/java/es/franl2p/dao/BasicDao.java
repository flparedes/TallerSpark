package es.franl2p.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public abstract class BasicDao<T> {
	
	// Hibernate factories
	private static final SessionFactory sessionFactory = configureSessionFactory();
	
	protected Session session;
	protected String entityName;

	private static SessionFactory configureSessionFactory() throws HibernateException {		
		// Check if there are environment config vars
		ProcessBuilder processBuilder = new ProcessBuilder();
        
        // Heroku got the DataBase parameters set as environments variables.
        if (processBuilder.environment().get("DATABASE_URL") != null) {
            return configureHeroku(processBuilder);
        } else {
        	return configureLocal();
        }
    }
	
	/**
	 * Configure the DataBase connection with the local settings in the hibernate.cfg.xml config file
	 * @return SessionFactory configured to run locally.
	 */
	private static SessionFactory configureLocal() {
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
        		.configure() // configures settings from hibernate.cfg.xml
    			.build();          
        return new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
	}
	
	/**
	 * Configure the DataBase connection with the settings in the hibernate.cfg.xml config file and
	 * overrided with the Heroku environment parameters.
	 * @return SessionFactory configured with Heroku parameters.
	 */
	private static SessionFactory configureHeroku(ProcessBuilder processBuilder) {
		Configuration configuration = new Configuration();
		
		String dbUrl = processBuilder.environment().get("JDBC_DATABASE_URL");
		String user = processBuilder.environment().get("JDBC_DATABASE_USERNAME");
		String pass = processBuilder.environment().get("JDBC_DATABASE_PASSWORD");

		configuration.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		configuration.setProperty("dialect", "org.hibernate.dialect.PostgreSQLDialect");
		configuration.setProperty("hibernate.connection.url", dbUrl);
		configuration.setProperty("hibernate.connection.username", user);
		configuration.setProperty("hibernate.connection.password", pass);
		configuration.setProperty("hibernate.driverClassName", "org.postgresql.Driver");
		configuration.configure();
		
		// configures settings from hibernate.cfg.xml and override settings
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure()    
	            .applySettings(configuration.getProperties()).build();
		
	    return configuration.buildSessionFactory(serviceRegistry);
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
	
	public boolean create(T entity) {
		boolean result = false;
		
		if (entity != null) {
			this.initTransaction();
			session.save(entity);
			this.endTransaction();
			result = true;
		}
		
		return result;
	}
	
	public boolean update(T entity) {
		boolean result = false;
		
		if (entity != null) {
			this.initTransaction();
			session.update(entity);
			this.endTransaction();
			result = true;
		}
		
		return result;
	}
	
	/**
	 * Deletes a car with the given id
	 * @param id Car identifier.
	 * @return true if deleted or false if not.
	 */
	public boolean delete(Integer id) {
		boolean result = false;
		
		T entity = findById(id);
		
		if (entity != null) {
			this.initTransaction();
			session.delete(entity);
			this.endTransaction();
			result = true;
		}
		
		return result;
	}
	
	/**
	 * Deletes a car with the given id
	 * @param id Car identifier.
	 * @return true if deleted or false if not.
	 */
	public boolean delete(T entity) {
		boolean result = false;
		
		if (entity != null) {
			this.initTransaction();
			session.delete(entity);
			this.endTransaction();
			result = true;
		}
		
		return result;
	}
	
	/**
	 * Sets the entityName.
	 */
	protected abstract void configureEntityName();
}
