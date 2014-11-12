package com.demo.bean;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Entity
@Name("school")
@Scope(SESSION)
@Table(name = "schools")
public class School implements Serializable {
	private static final long serialVersionUID = 4818188553954060418L;

	private int id;

	// Name of this school
	private String name;

	// whic campus
	private String location;

	public School() {
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotNull
	@Size(max = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@Size(max = 50)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "School(" + name + "," + location + ")";
	}
}
