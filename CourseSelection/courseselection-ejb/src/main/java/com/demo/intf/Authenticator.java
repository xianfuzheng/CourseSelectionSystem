package com.demo.intf;

import javax.ejb.Local;

/**
 * Interface for User Login
 */
@Local
public interface Authenticator {

	/**
	 * Verify this login attempt
	 * 
	 * @return valid or invalid authentication info
	 */
	boolean authenticate();
}
