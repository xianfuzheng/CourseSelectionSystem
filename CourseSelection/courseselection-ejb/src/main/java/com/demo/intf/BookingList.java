package com.demo.intf;

import javax.ejb.Local;

import com.demo.bean.Course;
import com.demo.bean.StudentBooking;

/**
 * 
 * Interface for booking a course
 * 
 * @author Frank ZHENG
 *
 */
@Local
public interface BookingList {

	/**
	 * Return all booked course for current user
	 */
	public void getBookings();

	/**
	 * Return current operating booking record
	 * 
	 * @return current operating booking record
	 */
	public StudentBooking getBooking();

	/**
	 * Cancel current operating booking
	 */
	public void cancelCourse(Course course);

	/**
	 * Clean up function ,should be invoked by framework
	 */
	public void destroy();
}