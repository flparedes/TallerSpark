package es.franl2p.services;

import es.franl2p.dao.DocumentDao;
import es.franl2p.model.Document;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentService {
	private final static Logger logger = LoggerFactory.getLogger(DocumentService.class);

	private DocumentDao documentDao = new DocumentDao();
	
	/**
	 * 
	 * @return
	 */
    public List<Document> findAll() {
    	List<Document> docs = documentDao.findAll();
    	logger.info("Documents found: " + docs);
    	
    	return docs;
    }
}
