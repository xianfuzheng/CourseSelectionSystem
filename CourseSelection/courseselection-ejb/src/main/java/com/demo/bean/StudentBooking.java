package com.demo.bean;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * Entity class for student booking record Refer to Course and WebUser
 * 
 * @author Frank ZHENG
 *
 */
@Entity
@Name("booking")
@Scope(SESSION)
@Table(name = "student_booking")
@TableGenerator(name="tabStudentBookingIDGenerator", initialValue=5, allocationSize=500)
public class StudentBooking implements Serializable {

	private static final long serialVersionUID = 4818188553954060410L;

	private int id;

	// who booked
	private WebUser user;

	// which course booked
	private Course course;

	// when booked
	private Date bookTime;

	public StudentBooking() {

	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="tabStudentBookingIDGenerator")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@NotNull
	public WebUser getUser() {
		return user;
	}

	public void setUser(WebUser user) {
		this.user = user;
	}

	@ManyToOne
	@NotNull
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Date getBookTime() {
		return bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}

	@Override
	public String toString() {
		return "StudentBooking(" + user + "," + course + ")";
	}
}
