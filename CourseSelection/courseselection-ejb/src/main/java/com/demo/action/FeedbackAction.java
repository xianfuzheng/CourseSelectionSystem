package com.demo.action;

import java.util.Map;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;

import com.demo.bean.FeedbackBean;
import com.demo.bean.FeedbackTypeEnum;
import com.demo.bean.WebUser;
import com.demo.intf.Feedback;

@Stateful
@Name("feedbackAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class FeedbackAction implements Feedback {

	@PersistenceContext
	private EntityManager em;

	private String type;
	private String content;
	
	@In
	private WebUser user;
	
	@In
	private FacesMessages facesMessages;
	
	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type= type;
	}

	@Override
	public String getContent() {
		
		return content;
	}

	@Override
	public void setContent(String content) {
		this.content= content;
	}
	
	@Override
	public void sendFeedBack(){
		String message = "";
		FeedbackBean feedback = new FeedbackBean();
		feedback.setType(type);
		feedback.setContent(content);
		feedback.setUser(user);
		try{
			em.persist(feedback);
			message = "We have received your feedback, thanks for your time.";
		}catch(Exception e){
			message = "Failed to send, please contact system administrator.";
		}
		 
		facesMessages.add(message);
		
		content = "";
		type = "";
	}

	
	
	@Override
	public Map<String, Object> getFeedBackTypes() {
		return FeedbackTypeEnum.getTypeMap();
	}

	@Remove
	public void destroy() {
		
	}

}
