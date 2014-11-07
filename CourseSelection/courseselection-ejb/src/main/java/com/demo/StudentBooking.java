
package com.demo;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;


import java.util.Date;

import javax.validation.constraints.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Entity
@Name("studentBooking")
@Scope(SESSION)
@Table(name="student_booking")
public class StudentBooking implements Serializable
{
   private static final long serialVersionUID = 4818188553954060410L;
   
   private int id;
   private WebUser user;
   private Course course;
   private Date bookTime; 
   
   public StudentBooking() {
   
   }

   @Id @GeneratedValue
   public int getId()
   {
      return id;
   }
   public void setId(int id)
   {
      this.id = id;
   }

  @ManyToOne @NotNull
   public WebUser getUser(){
		return user;
   }

   public void setUser(WebUser user){
	 this.user = user;
   }
   
   @ManyToOne @NotNull
   public Course getCourse(){
		return course;
   }
   
   public void setCourse(Course course){
	 this.course = course;
   }

   public Date getBookTime(){
		return bookTime;
   }
   
   public void setBookTime(Date bookTime){
	 this.bookTime = bookTime;
   }


   @Override
   public String toString() 
   {
      return "StudentBooking(" + user + ","+course+")";
   }
}
