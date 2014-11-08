package com.demo.intf;

import javax.ejb.Local;

import com.demo.bean.Course;

/**
 * Interface for user to book a course
 * 
 * @author Frank ZHENG
 *
 */
@Local
public interface CourseBooking {

	/**
	 * User confirm to book this course
	 * 
	 * @param selectedCourse
	 *            Framework inject object
	 */
	public void selectCourse(Course selectedCourse);

	/**
	 * Cleanup function ,should be invoked by framework
	 */
	public void destroy();

}