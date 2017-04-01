package es.franl2p.services;

import es.franl2p.dao.DocumentDao;
import es.franl2p.dao.UserDao;
import es.franl2p.model.Document;
import es.franl2p.model.User;
import spark.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentService {
	private final static Logger logger = LoggerFactory.getLogger(DocumentService.class);

	private DocumentDao documentDao = new DocumentDao();
	private UserDao userDao = new UserDao();
	
	/**
	 * 
	 * @return
	 */
    public List<Document> findAll() {
    	List<Document> docs = documentDao.findAll();
    	logger.info("Documents found: " + docs);
    	
    	return docs;
    }
	
	/**
	 * 
	 * @return
	 */
    public Document findById(Integer id) {
    	Document doc = documentDao.findById(id);
    	logger.info("Document found: " + doc);
    	
    	return doc;
    }
    
    /**
     * Inserta el nuevo documento en la Base de Datos.
     * @param fileName Nombre del documento a insertar.
     * @param is InputStream con el contenido del fichero a insertar.
     * @param userName Nombre del usuario que realiza la subida.
     * @return Resultado de la inserción.
     * @throws IOException Excepción en caso de que falle la lectura del contenido.
     */
    public boolean insert(Document documento) throws IOException {
    	return documentDao.create(documento);
    }
    
    /**
     * Inserta el nuevo documento en la Base de Datos.
     * @param fileName Nombre del documento a insertar.
     * @param is InputStream con el contenido del fichero a insertar.
     * @param userName Nombre del usuario que realiza la subida.
     * @return Resultado de la inserción.
     * @throws IOException Excepción en caso de que falle la lectura del contenido.
     */
    public boolean insert(String fileName, InputStream is, String userName) throws IOException {
    	byte[] data = inputStream2ByteArray(is);
    	User user = userDao.findByName(userName);
    	return documentDao.create(new Document(null, fileName, data, user));
    }
    
    private byte[] inputStream2ByteArray(InputStream is) throws IOException {
    	byte[] data = IOUtils.toByteArray(is);
    	is.close();
    	return data;
    }
}
