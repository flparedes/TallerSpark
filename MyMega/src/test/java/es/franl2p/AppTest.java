package es.franl2p;

import org.mindrot.jbcrypt.BCrypt;

import es.franl2p.utils.Constants;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Test pass encrypt with a given salt
     */
    public void testApp()
    {
    	String salt = BCrypt.gensalt(12);
    	String pass = "narF";
    	String wrongPass = "wrongPass";
    	
//    	// gensalt's log_rounds parameter determines the complexity
//    	// the work factor is 2**log_rounds, and the default is 10
//    	String hashed = BCrypt.hashpw(pass, BCrypt.gensalt(12));
//
//    	// Check that an unencrypted password matches one that has
//    	// previously been hashed
//    	if (BCrypt.checkpw(pass, hashed)) {
//    		logger.info("It matches");
//    		session.attribute(Constants.USER_SESSION, userName);
//    		authenticated = true;
//    	} else {
//    		logger.info("It does not match");
//    	}
    	
    	String hashedPass = BCrypt.hashpw(pass, salt);
    	
    	assertTrue(BCrypt.checkpw(pass, hashedPass));
    	assertFalse(BCrypt.checkpw(wrongPass, hashedPass));
    }
}
