package es.franl2p.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import es.franl2p.model.Document;

public class DocumentDao extends BasicDao<Document> {
	
	public DocumentDao() {
		this.configureEntityName();
	}
	
	/**
	 * Gets all the Documents.
	 * @return 
	 */
	public List<Document> findAll() {
		this.initTransaction();
		
		@SuppressWarnings("unchecked")
		List<Document> documents = session.createQuery("from Document").list();
		this.endTransaction();
		
		return documents;
	}
	
	/**
	 * Finds and gets the document with given document name.
	 * @param name The document's name
	 * @return The document found or null if not found.
	 */
	public Document findByName(String name) {
		Document document = null;
		
		this.initTransaction();
		try {
			Criteria crit = session.createCriteria(this.entityName);
			crit.add(Restrictions.eq("name", name));
			
			@SuppressWarnings("unchecked")
			List<Document> documents = crit.list();
			
			if (documents != null && !documents.isEmpty()) {
				document = documents.get(0);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		
		this.endTransaction();
		
		return document;
	}

	@Override
	protected void configureEntityName() {
		this.entityName = Document.class.getName();
	}
}
