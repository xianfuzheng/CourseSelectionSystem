package com.demo.bean;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Entity
@Name("recommendation")
@Scope(SESSION)
@Table(name = "recommendation")
public class Recommendation implements Serializable {
	private static final long serialVersionUID = 4818188553954060410L;

	private String username;
	private String password;
	private String role;

	public Recommendation() {
	}

	@NotNull
	@Size(max = 100)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@NotNull
	@Size(min = 5, max = 15)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Id
	@Size(min = 4, max = 15)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User(" + username + "," + role + ")";
	}
}
