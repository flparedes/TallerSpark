package es.franl2p.utils;

import javax.servlet.http.Part;

public class DocumentUtils {

	/**
	 * Gets the file name from the part's header info.
	 * @param part Document part send by user.
	 * @return The document's name or "document" if cannot be readed.
	 */
	public static String getFileName(Part part) {
		String docName = "document";
		String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
            	docName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            	docName = docName.replace("\\", "/");
            	docName = docName.substring(docName.lastIndexOf("/") + 1);
            }
        }
        return docName;
    }
	
	/**
	 * Extracts the mime type from the part.
	 * @param part Document part send by user.
	 * @return The document's mime type or "application/octet-stream" if cannot be extracted.
	 */
	public static String getType(Part part) {
	    String type = part != null ? part.getContentType() : "application/octet-stream";
	    
	    return type;
	}
}
