package com.demo.action;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;
import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.demo.bean.Course;
import com.demo.bean.StudentBooking;
import com.demo.bean.WebUser;
import com.demo.intf.BookingList;

@Stateful
@Scope(SESSION)
@Name("bookingList")
@Restrict("#{identity.loggedIn}")
@TransactionAttribute(REQUIRES_NEW)
public class BookingListAction implements BookingList, Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	@In
	private WebUser user;

	@DataModel
	private List<StudentBooking> bookings;

	@DataModelSelection
	private StudentBooking booking;

	@Logger
	private Log log;

	public void getBookings() {
		bookings = em
				.createQuery(
						"select b from StudentBooking b where b.user.username = :username order by b.bookTime")
				.setParameter("username", user.getUsername()).getResultList();
	}

	public StudentBooking getBooking() {
		return booking;
	}

	public void cancelCourse(Course course) {

		log.info("Cancel booking: #{bookingList.booking.id} for #{user.username}");
		StudentBooking cancelled = em.find(StudentBooking.class,
				booking.getId());
		log.info("cancelled=" + cancelled);

		if (cancelled != null)
			em.remove(cancelled);

		getBookings();

		FacesMessages.instance().add("Cancelled successfully");
	}

	@Remove
	public void destroy() {
		log.debug(this + " has been destroyed");
	}
}
