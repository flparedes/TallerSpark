package es.franl2p.services;

import es.franl2p.dao.UserDao;
import es.franl2p.model.User;
import es.franl2p.utils.Constants;
import spark.*;

import org.mindrot.jbcrypt.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginService {
	private final static Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	private UserDao userDao = new UserDao();

    /**
     * Checks if the user is logged in or not.
     * @param request Spark request
     * @param response Spark response
     * @return
     */
    public boolean isLoggedIn(Request request, Response response) {
    	boolean isLoggedIn = (request.session().attribute(Constants.USER_SESSION) != null);
        
        return isLoggedIn;
    }
    
    /**
     * Gets the logged in user name.
     * @param session Session where the user name is stored.
     * @return The logged in user name or if there are no user logged.
     */
    public User getLoggedInUser(Session session) {
    	String userName = this.getLoggedInUserName(session);
    	User user = userDao.findByName(userName);
    	
    	return user;
    }
    
    /**
     * Gets the logged in user name.
     * @param session Session where the user name is stored.
     * @return The logged in user name or if there are no user logged.
     */
    public String getLoggedInUserName(Session session) {
    	String userName = session.attribute(Constants.USER_SESSION);
    	
    	return userName;
    }
    
    /**
     * Checks if the user is logged in or not. If it's not logged then redirects to the login page.
     * The origin of the request (request.pathInfo()) is saved in the session to the user can be 
     * redirected back after login.
     * @param request Spark request
     * @param response Spark response
     */
    public void checkLoggedIn(Request request, Response response) {
        if (!this.isLoggedIn(request, response)) {
            request.session().attribute(Constants.PATH_AFTER_LOGIN, request.pathInfo());
            response.redirect(Constants.LOGIN_ROUTE);
        }
    }
    
    /**
     * Logs in the user and save the users name in session. If the user name and password
     * doesn't match the user isn't logged in and returns false.
     * @param userName The user's name
     * @param pass The user's password
     * @param session The session where the user name will be stored.
     * @return true if logged in or false if it's not.
     */
    public boolean loginUser(String userName, String pass, Session session) {
    	boolean authenticated = false;
    	
    	if (!userName.isEmpty() && !pass.isEmpty()) {
	        User user = userDao.findByName(userName);
	        if (user != null) {
	        	// Hash the password with the user salt
		        String hashed = BCrypt.hashpw(pass, user.getSalt());
		
		    	// Check that the user encrypted password matches the password just hashed
		    	if (hashed.equals(user.getPass())) {
		    		logger.info("It matches");
		    		session.attribute(Constants.USER_SESSION, userName);
		    		authenticated = true;
		    	} else {
		    		logger.info("It does not match");
		    	}
	        }
        }
    	
    	return authenticated;
    }
    
    /**
     * Logs out the user and removes the users name from the session.
     * @param session The session where the user name is stored.
     */
    public void logoutUser(Session session) {
    	session.removeAttribute(Constants.USER_SESSION);
    }
}
