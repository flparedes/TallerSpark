package es.franl2p.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Constants {
	// Session parameters
	public static final String USER_SESSION = "userSession";
	public static final String PATH_AFTER_LOGIN = "pathAfterLogin";
	
	// Routes constants
	public static final String MAIN_ROUTE = "/";
	public static final String LOGIN_ROUTE = "/login";
	public static final String LOGOUT_ROUTE = "/logout";
	public static final String NEW_DOC_ROUTE = "/new";
	public static final List<String> PROTECTED_ROUTES;
	static {
        List<String> protectedRoutes = new LinkedList<String>();
        protectedRoutes.add("/new");

        PROTECTED_ROUTES = Collections.unmodifiableList(protectedRoutes);
    }
}
