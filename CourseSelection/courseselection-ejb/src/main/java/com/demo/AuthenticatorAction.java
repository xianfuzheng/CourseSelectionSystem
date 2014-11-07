package com.demo;

import static org.jboss.seam.ScopeType.SESSION;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

@Stateless
@Name("authenticator")
public class AuthenticatorAction 
    implements Authenticator
{
    @PersistenceContext 
    private EntityManager em;

    @In(required=false)   
    @Out(required=false, scope = SESSION)
    private WebUser user;
   
    public boolean authenticate()
    {
		List results = em.createQuery("select u from WebUser u where u.username=#{identity.username} and u.password=#{identity.password}")
							 .getResultList();
		  
		if (results.size()==0) {
			return false;
		} else {
			user = (WebUser) results.get(0);
			return true;
		}
   }    
}