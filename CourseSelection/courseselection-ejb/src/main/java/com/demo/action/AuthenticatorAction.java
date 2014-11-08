package com.demo.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.log.Log;

import com.demo.bean.WebUser;
import com.demo.intf.Authenticator;

/**
 * Action invoked by framework to authenticate user Put WebUser object into
 * session for later usage
 * 
 * @author Frank Zheng
 *
 */
@Stateless
@Name("authenticator")
public class AuthenticatorAction implements Authenticator {

	@PersistenceContext
	private EntityManager em;

	@In(required = false)
	@Out(required = false, scope = SESSION)
	private WebUser user;

	@Logger
	private Log log;
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demo.Authenticator#authenticate()
	 */
	public boolean authenticate() {
		
		List results = em
				.createQuery(
						"select u from WebUser u where u.username=#{identity.username} and u.password=#{identity.password}")
				.getResultList();

		if (results.size() == 0) {
			return false;
		} else {
			
			// put webuser object into conversation
			Object obj = results.get(0);
			if(user instanceof WebUser){
				user = (WebUser) obj;
				return true;
			}else{
				//error
				log.error("Login failed, reason :user is not a instance of WebUser.");
				return false;
			}
		}
	}
}
