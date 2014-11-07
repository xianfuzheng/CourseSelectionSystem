package com.demo;

import static javax.persistence.PersistenceContextType.EXTENDED;

import java.util.Calendar;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

@Stateful
@Name("courseBooking")
@Restrict("#{identity.loggedIn}")
public class CourseBookingAction implements CourseBooking
{
   
   @PersistenceContext(type=EXTENDED)
   private EntityManager em;
   
   @In 
   private WebUser user;
 
   @Logger 
   private Log log;
   

   public void selectCourse(Course selectedCourse)
   {
	  //check whether the user has booked or not

      StudentBooking studentBooking = new StudentBooking();
	  studentBooking.setCourse(selectedCourse);
	  studentBooking.setUser(user);
	  em.persist(studentBooking);
   }
   
   @Remove
   public void destroy() {}
}