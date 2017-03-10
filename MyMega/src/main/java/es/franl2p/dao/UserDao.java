package es.franl2p.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import es.franl2p.model.User;

public class UserDao extends BasicDao<User> {
	
	public UserDao() {
		this.configureEntityName();
	}
	
	/**
	 * Gets all the Users.
	 * @return 
	 */
	public List<User> findAll() {
		this.initTransaction();
		
		@SuppressWarnings("unchecked")
		List<User> users = session.createQuery("from User").list();
		this.endTransaction();
		
		return users;
	}
	
	/**
	 * Finds and gets the user with given user name.
	 * @param name The user's name
	 * @return The user found or null if not found.
	 */
	public User findByName(String name) {
		User user = null;
		
		this.initTransaction();
		try {
			Criteria crit = session.createCriteria(this.entityName);
			crit.add(Restrictions.eq("name", name));
			
			@SuppressWarnings("unchecked")
			List<User> users = crit.list();
			
			if (users != null && !users.isEmpty()) {
				user = users.get(0);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		
		this.endTransaction();
		
		return user;
	}

	@Override
	protected void configureEntityName() {
		this.entityName = User.class.getName();
	}
}
