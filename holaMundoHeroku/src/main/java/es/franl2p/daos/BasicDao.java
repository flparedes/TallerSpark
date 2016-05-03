package es.franl2p.daos;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public abstract class BasicDao {
	
	// Hibernate factories
	private static final SessionFactory sessionFactory = configureSessionFactory();
	
	protected Session session;

	private static SessionFactory configureSessionFactory() throws HibernateException {  
        Configuration configuration = new Configuration();  
        configuration.configure();  
         
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
}
