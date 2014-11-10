package com.demo.bean;

import static org.jboss.seam.ScopeType.SESSION;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.demo.annotation.Enum;

@Entity
@Name("feedback")
@Table(name="feedbacks")
@Scope(SESSION)
public class FeedbackBean {

	
	private int id;
	
	private WebUser user;
	
	private String type;
	private String content;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@NotNull
	@Enum(enumClass=FeedbackTypeEnum.class)
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@NotNull
	@Size(min=10)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@ManyToOne
	@NotNull
	public WebUser getUser() {
		return user;
	}
	public void setUser(WebUser user) {
		this.user = user;
	}
	
	
}
